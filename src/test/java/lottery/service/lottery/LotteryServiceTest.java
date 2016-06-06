package lottery.service.lottery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lottery.entity.Winner;
import lottery.service.lottery.LotteryService;
import lottery.exception.DrawFinishedException;
import lottery.exception.MinParticipantsNotReachException;
import lottery.exception.NoAvailableSpotException;
import lottery.exception.UnknowCommandExcpetion;
import lottery.exception.WinnerNotDrawnException;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LotteryServiceTest
{
    private LotteryService service;
    private Output output;
    private Scanner scanner;
    private final ByteArrayOutputStream outContent =  new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent =  new ByteArrayOutputStream();

    @Before
    public void setUp() 
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
        this.service = new LotteryService();
        this.output  = new Output();
        this.scanner = new Scanner(System.in);
        this.service.run(scanner);
    }

    @After
    public void cleanUpStreams() 
    {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void testRunPurchaseCmd()
    {
        assertEquals(Output.COMMAND_WAITING, outContent.toString());
        ByteArrayInputStream in = new ByteArrayInputStream("purchase\r\nChris\r\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        this.service.run(scanner);
        assertEquals(Output.COMMAND_WAITING + Output.COMMAND_WAITING + "\n" + 
                Output.COMMAND_WAITING_NAME + 
                "Thanks for you purchase, your ball number is: 1" + "\n\n" + Output.COMMAND_WAITING, 
                outContent.toString());
        System.setIn(in);
    }

    @Test
    public void testRunUnknowCommandExcpetion()
    {
        assertEquals(Output.COMMAND_WAITING, outContent.toString());
        ByteArrayInputStream in = new ByteArrayInputStream("Unkown Command\r\n".getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);
        this.service.run(scanner);
        assertEquals(Output.COMMAND_WAITING + Output.COMMAND_WAITING + "\n" + Output.COMMAND_WAITING, outContent.toString());
        assertEquals("Unknow command\n", errContent.toString());
        System.setIn(in);
    }


    @Test    
    public void testPurchaseCommand() throws MinParticipantsNotReachException, DrawFinishedException,
            WinnerNotDrawnException, NoAvailableSpotException, UnknowCommandExcpetion    {
        try {
            String resultPrepare = service.processCommand(InputCommand.COMMAND_TO_PURCHASE);
            assertEquals("", resultPrepare);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test    
    public void testDoPurchaseCommand() throws UnknowCommandExcpetion
    {
        try {
            String result1 = service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Chris");
            String result2 = service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Robert");
            String result3 = service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Tomaz");
            String result4 = service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Froze");
            assertEquals(output.getDisplayPurchase(1), result1);
            assertEquals(output.getDisplayPurchase(2), result2);
            assertEquals(output.getDisplayPurchase(3), result3);
            assertEquals(output.getDisplayPurchase(4), result4);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }
    
    @Test
    public void testDrawWinnerCommand() throws MinParticipantsNotReachException, DrawFinishedException,
            WinnerNotDrawnException, NoAvailableSpotException, UnknowCommandExcpetion    {
        try {
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Chris");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Robert");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Tomaz");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Froze");
 
            String result = service.processCommand(InputCommand.COMMAND_TO_DRAW);
            assertEquals(Output.WINNBER_DRAW_FINISHED, result);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test
    public void testDisplayWinnersCommand() throws MinParticipantsNotReachException, DrawFinishedException,
            WinnerNotDrawnException, NoAvailableSpotException, UnknowCommandExcpetion    {
        try {

            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Chris");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Robert");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Tomaz");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Froze");
            service.processCommand(InputCommand.COMMAND_TO_DRAW);

            String result        = service.processCommand(InputCommand.COMMAND_TO_DISPLAY_WINNERS);
            List<Winner> winners = service.getWinners();
            String winnersOutput = output.getDisplayWinners(winners);
            assertEquals(result, winnersOutput);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test
    public void testRestartCoammand() throws MinParticipantsNotReachException, DrawFinishedException, WinnerNotDrawnException, NoAvailableSpotException, UnknowCommandExcpetion    {
        try {
            String result = service.processCommand(InputCommand.COMMAND_TO_RESTART);
            assertEquals(result, Output.NEW_DRAW_START);
        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test
    public void testMinParticipantsNotReachException() throws UnknowCommandExcpetion, DrawFinishedException, WinnerNotDrawnException, NoAvailableSpotException {
        try {        
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Chris");
            service.processCommand(InputCommand.COMMAND_TO_DRAW);

        } catch (MinParticipantsNotReachException e) {
            assertEquals("Minimum participants not reach", e.getMessage());
        }
    }

    @Test
    public void testDrawFinishedException() throws UnknowCommandExcpetion, MinParticipantsNotReachException, WinnerNotDrawnException, NoAvailableSpotException {
        try {        
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Chris");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Robert");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Tomaz");
            service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Froze");
            service.processCommand(InputCommand.COMMAND_TO_DRAW);
            service.processCommand(InputCommand.COMMAND_TO_DRAW);

        } catch (DrawFinishedException e) {
            assertEquals("Lottery has been drawn, no purcahse is allowed", e.getMessage());
        }
    }

    @Test
    public void testWinnerNotDrawnException() throws MinParticipantsNotReachException, DrawFinishedException, NoAvailableSpotException, UnknowCommandExcpetion    
    {
        try {        
            service.processCommand(InputCommand.COMMAND_TO_DISPLAY_WINNERS);

        } catch (WinnerNotDrawnException e) {
            assertEquals("Lottery has not been drawn, no winner found", e.getMessage());
        }
    }


    @SuppressWarnings("static-access")
    @Test
    public void testNoAvailableSpotException() throws MinParticipantsNotReachException, DrawFinishedException, WinnerNotDrawnException, UnknowCommandExcpetion    
    {
        try {
            int counter = 0;
            while (counter <= service.getMaxParticipants()) {
                service.processCommand(InputCommand.COMMAND_DO_PURCHASE, "Tomaz");
                counter++;
            }
            service.processCommand(InputCommand.COMMAND_TO_PURCHASE);
        } catch (NoAvailableSpotException e) {
            assertEquals("No available spot in this draw", e.getMessage());
        }
    }

    @Test
    public void testUnknowCommandExcpetion() throws MinParticipantsNotReachException, DrawFinishedException, NoAvailableSpotException, WinnerNotDrawnException
    {
        try {        
            service.processCommand("Unknow command");

        } catch (UnknowCommandExcpetion e) {
            assertEquals("Unknow command", e.getMessage());
        }
    }

}
