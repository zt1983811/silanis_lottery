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
     * {@value #INIT_POT}
     */
    private final static int INIT_POT            = 200;

    /**
     * {@value #MAX_PARTICIPANTS}
     */
    private final static int MAX_PARTICIPANTS    = 50;

    /**
     * {@value #MIN_PARTICIPANTS}
     */
    private final static int MIN_PARTICIPANTS    = 3;

    /**
     * {@value #NUMBER_OF_WINNERS}
     */
    private final static int NUMBER_OF_WINNERS   = 3;

    /**
     * {@value #PRICE_FOR_EACH_BALL}
     */
    private final static int PRICE_FOR_EACH_BALL = 10;

    /**
     * {@value #PRIZE_RATE}
     */   
    private final static double[] PRIZE_RATE = new double[] { 0.75, 0.15, 0.10 };

    /**
     * {@value #draw}
     */
    private Draw draw;

    /**
     * {@value #participants}
     */
    private List<Participant> participants = new ArrayList<Participant>();

    /**
     * {@value #winners}
     */
    private List<Winner> winners = new ArrayList<Winner>();

    /**
     * {@value #drawIndex}
     */
    private int drawIndex;

    /**
     * {@value #ballNumber}
     */
    private int ballNumber;

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
                if (command.equalsIgnoreCase(InputCommand.COMMAND_TO_PURCHASE)) {
                    String firstName = getPurchanseFirstName(scanner);
                    System.out.println(processCommand(InputCommand.COMMAND_DO_PURCHASE, firstName));
                }

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
                return output.getDisplayWinners(this.winners);

            default:
                break;
        }

        throw new UnknowCommandExcpetion("Unknow command");
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
                return output.getDisplayPurchase(this.ballNumber - 1);

            default:
                break;
        }

        throw new UnknowCommandExcpetion("Unknow command");
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
        this.winners.clear();
        this.participants.clear();
        this.drawIndex = 0;
        this.ballNumber = 1;
        this.draw = new Draw(INIT_POT, PRICE_FOR_EACH_BALL, this.participants, this.winners);
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
        if (drawIndex >= MAX_PARTICIPANTS) {
            throw new NoAvailableSpotException("No available spot in this draw");
        }

        if (!this.winners.isEmpty()) {
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
        Participant participant = new Participant(firstName, this.ballNumber);
        this.participants.add(this.drawIndex, participant);
        this.draw.setPot(this.draw.getPot() + PRICE_FOR_EACH_BALL);
        this.drawIndex++;
        this.ballNumber++;
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
        if (this.participants.size() < MIN_PARTICIPANTS && this.winners.isEmpty()) {
            throw new MinParticipantsNotReachException("Minimum participants not reach");
        }

        if (!this.winners.isEmpty()) {
            throw new DrawFinishedException("Lottery has been drawn, no purcahse is allowed");
        }

        int prizeCounter = 0;
        while (this.winners.size() < NUMBER_OF_WINNERS)
        {
            Collections.shuffle(this.participants);
            double prize  = this.draw.getPot() / 2 * PRIZE_RATE[prizeCounter];
            Winner winner = new Winner(participants.get(0), prize);
            this.winners.add(prizeCounter, winner);
            this.participants.remove(0);
            prizeCounter++;
        }
    }

    /**
     *
     * @return the maxParticipants
     */
    public static int getMaxParticipants() 
    {
        return MAX_PARTICIPANTS;
    }

    /**
     *
     * @return the winners
     */
    public List<Winner> getWinners() 
    {
        return winners;
    }
}
