package lottery.service.lottery;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import lottery.entity.Participant;
import lottery.entity.Winner;
import lottery.service.lottery.LotteryService;
import lottery.exception.DrawFinishedException;
import lottery.exception.MinParticipantsNotReachException;
import lottery.exception.NoAvailableSpotException;
import lottery.exception.UnknowCommandExcpetion;
import lottery.exception.WinnerNotDrawnException;

public class OutputTest
{
    private Output output;
    private List<Winner> winners = new ArrayList<Winner>();

    @Before
    public void setUp() 
    {
        this.output = new Output();
        this.winners.add(0, new Winner(new Participant("Tom", 1), 86.25));       
        this.winners.add(1, new Winner(new Participant("Chris", 2), 17.25));       
        this.winners.add(2, new Winner(new Participant("Forget", 3), 11.5));       
    }

    public static final String WINNBER_DRAW_FINISHED = "Winnber has been drawn";
    public static final String NEW_DRAW_START        = "New draw has been start";
    public static final String COMMAND_WAITING       = "Please enter commands [purchase, draw, winners, restart, quit]: ";
    public static final String COMMAND_WAITING_NAME  = "Please enter first name: ";

    @Test    
    public void testOutputText() 
    {
        try {
            assertEquals("Winnber has been drawn", Output.WINNBER_DRAW_FINISHED);
            assertEquals("New draw has been start", Output.NEW_DRAW_START);
            assertEquals("Please enter commands [purchase, draw, winners, restart, quit]: ", Output.COMMAND_WAITING);
            assertEquals("Please enter first name: ", Output.COMMAND_WAITING_NAME);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test    
    public void testGetDisplayPurchase() 
    {
        try {
            assertEquals("Thanks for you purchase, your ball number is: 1", output.getDisplayPurchase(1));
            assertEquals("Thanks for you purchase, your ball number is: 4", output.getDisplayPurchase(4));
            assertEquals("Thanks for you purchase, your ball number is: 10", output.getDisplayPurchase(10));
            assertEquals("Thanks for you purchase, your ball number is: 50", output.getDisplayPurchase(50));

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test    
    public void testGetDisplayWinners() throws WinnerNotDrawnException
    {
        try {
            String response = "1st ball 2nd ball 3rd ball" + "\n\n" + "Tom: 86.25$ Chris: 17.25$ Forget: 11.5$";
            assertEquals(response, output.getDisplayWinners(this.winners));

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }

    @Test    
    public void testWinnerNotDrawnException()
    {
        try {
            List<Winner> emptyWinners = new ArrayList<Winner>();
            output.getDisplayWinners(emptyWinners);
        } catch (WinnerNotDrawnException e) {
            String response = "Lottery has not been drawn, no winner found";
            assertEquals(response, e.getMessage());
        }
    }
}
