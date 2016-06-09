package lottery.service.lottery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import lottery.entity.Draw;
import lottery.entity.Participant;
import lottery.entity.Winner;
import lottery.exception.BaseException;
import lottery.exception.DrawFinishedException;
import lottery.exception.MinParticipantsNotReachException;
import lottery.exception.NoAvailableSpotException;
import lottery.exception.UnknowCommandExcpetion;
import lottery.exception.WinnerNotDrawnException;
import lottery.service.ServiceInterface;

/**
 * Lottery service class
 *
 * Description: Lottery service to process all input
 *
 * @author Tong Zhou
 */
public class LotteryService implements ServiceInterface
{
    /**
     * {@value #draw}
     */
    private Draw draw;

    /**
     * {@value #output}
     */
    private Output output = new Output();
    
    /**
     * Run the service
     *
     * @param Scanner scanner
     */
    @Override
    public void run(Scanner scanner)
    {
        String command = "";
        System.out.print(Output.COMMAND_WAITING);
        initDraw();

        while (scanner.hasNextLine()) {
            command = scanner.nextLine();

            if (command.equalsIgnoreCase(InputCommand.COMMAND_TO_QUIT)) {
                break;
            }

            try {
                System.out.println(processCommand(command));
                doPurchaseStepHandler(command, scanner);

            } catch (BaseException e) {
                System.err.println(e.getMessage());
            }

            System.out.print("\n" + Output.COMMAND_WAITING);
        }
    }

    /**
     * Process command line
     *
     * @param String command
     * @return String
     *
     * @throws MinParticipantsNotReachException
     * @throws DrawFinishedException
     * @throws WinnerNotDrawnException
     * @throws NoAvailableSpotException
     * @throws UnknowCommandExcpetion
     */
    public String processCommand(String command) throws MinParticipantsNotReachException, DrawFinishedException, 
        WinnerNotDrawnException, NoAvailableSpotException, UnknowCommandExcpetion 
    {
        switch(command.toLowerCase()) {

            case InputCommand.COMMAND_TO_RESTART:
                initDraw();
                return Output.NEW_DRAW_START;

            case InputCommand.COMMAND_TO_PURCHASE: 
                preparePurchaseDraw();
                return "";

            case InputCommand.COMMAND_TO_DRAW: 
                drawWinner();
                return Output.WINNNER_DRAW_FINISHED;

            case InputCommand.COMMAND_TO_DISPLAY_WINNERS:
                return output.getDisplayWinners(this.draw.getWinners());

            default:
                throw new UnknowCommandExcpetion("Unknow command");
        }
    }

    /**
     * Overload process command with the first name being pass for do purchase
     *
     * @param String command
     * @param String firstName
     * @return String
     *
     * @throws UnknowCommandExcpetion
     */
    public String processCommand(String command, String firstName) throws UnknowCommandExcpetion
    {
        switch(command.toLowerCase()) {

            case InputCommand.COMMAND_DO_PURCHASE: 
                purchaseDraw(firstName);
                int currentBallNumber = getBallNumberByDrawIndex(this.draw.getParticipants().size()) - 1;
                return output.getDisplayPurchase(currentBallNumber);

            default:
                throw new UnknowCommandExcpetion("Unknow command");
        }

    }

    /**
     * Do puchase handler step
     *
     * @param previousCommand
     * @param scanner
     *
     * @throws UnknowCommandExcpetion
     */
    private void doPurchaseStepHandler(String previousCommand, Scanner scanner) throws UnknowCommandExcpetion
      {
        if (previousCommand.equalsIgnoreCase(InputCommand.COMMAND_TO_PURCHASE)) {
            String firstName = getPurchanseFirstName(scanner);
            System.out.println(processCommand(InputCommand.COMMAND_DO_PURCHASE, firstName));
        }
    }

    /**
     * Scan user input first name and return
     *
     * @param Scanner scanner
     * @return String
     */
    private String getPurchanseFirstName(Scanner scanner)
    {
        System.out.print(Output.COMMAND_WAITING_NAME);
        String firstName = scanner.nextLine();
        return firstName;
    }
    
    /**
     * Init the draw
     *
     */
    private void initDraw()
    {
        List<Participant> participants = new ArrayList<Participant>();
        List<Winner> winners = new ArrayList<Winner>();
        this.draw = new Draw(Rules.INIT_POT, Rules.PRICE_FOR_EACH_BALL, participants, winners);
    }

    /**
     *
     * Prepare for purchase the draw
     *
     * @throws NoAvailableSpotException
     * @throws DrawFinishedException
     */
    private void preparePurchaseDraw() throws NoAvailableSpotException, DrawFinishedException
    {
        if (this.draw.getParticipants().size() >= Rules.MAX_PARTICIPANTS) {
            throw new NoAvailableSpotException("No available spot in this draw");
        }

        if (!this.draw.getWinners().isEmpty()) {
            throw new DrawFinishedException("Lottery has been drawn, no purcahse is allowed");
        }
    }

    /**
     * do Purchase the draw
     *
     * @param String firstName
     */
    private void purchaseDraw(String firstName)
    {
        int currentParticipantIndex    = this.draw.getParticipants().size();
        int ballNumber                 = getBallNumberByDrawIndex(currentParticipantIndex);
        List<Participant> participants = this.draw.getParticipants();
        Participant participant        = new Participant(firstName, ballNumber);
        participants.add(currentParticipantIndex, participant);
        this.draw.setParticipants(participants);
        this.draw.setPot(this.draw.getPot() + Rules.PRICE_FOR_EACH_BALL);
    }

    /**
     * Return ball number by draw index for uniqueness
     *
     * @param drawIndex
     * @return
     */
    private int getBallNumberByDrawIndex(int drawIndex) 
    {
        return drawIndex + 1;
    }

    /**
     * Check if min participants are reached
     *
     * @param participants
     * @param winners
     * @return
     */
    private boolean isMinParticipantsReached(List<Participant> participants, List<Winner> winners)
    {
        return (participants.size() < Rules.MIN_PARTICIPANTS && winners.isEmpty());
    }

    /**
     *
     * Draw the winner in current round
     *
     * @throws MinParticipantsNotReachException
     * @throws DrawFinishedException
     */
    private void drawWinner() throws MinParticipantsNotReachException, DrawFinishedException
    {
        if (isMinParticipantsReached(this.draw.getParticipants(), this.draw.getWinners())) {
            throw new MinParticipantsNotReachException("Minimum participants not reach");
        }

        if (!this.draw.getWinners().isEmpty()) {
            throw new DrawFinishedException("Lottery has been drawn, no purcahse is allowed");
        }

        List<Participant> participants = this.draw.getParticipants();
        this.draw.setWinners(getRandomWinnersByParticipants(participants));
    }

    /**
     * Return random winners from participant
     *
     * @param participants
     * @return List<Winner> winners
     */
    private List<Winner> getRandomWinnersByParticipants(List<Participant> participants)
    {
        int prizeCounter     = 0;
        List<Winner> winners = new ArrayList<Winner>();

        while (winners.size() < Rules.NUMBER_OF_WINNERS)
        {
            Collections.shuffle(participants);
            double prize  = this.draw.getPot() / 2 * Rules.PRIZE_RATE[prizeCounter];
            Winner winner = new Winner(participants.get(0), prize);
            winners.add(prizeCounter, winner);
            participants.remove(0);
            prizeCounter++;
        }   

        return winners;
    }

    /**
     *
     * @return the draw
     */
    public Draw getDraw() 
    {
        return this.draw;
    }
}
