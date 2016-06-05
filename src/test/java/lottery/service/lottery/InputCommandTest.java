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

public class InputCommandTest
{
    @Test    
    public void testCommand() 
    {
        try {
            assertEquals("quit", InputCommand.COMMAND_TO_QUIT);
            assertEquals("restart", InputCommand.COMMAND_TO_RESTART);
            assertEquals("winners", InputCommand.COMMAND_TO_DISPLAY_WINNERS);
            assertEquals("draw", InputCommand.COMMAND_TO_DRAW);
            assertEquals("purchase", InputCommand.COMMAND_TO_PURCHASE);
            assertEquals("do purchase", InputCommand.COMMAND_DO_PURCHASE);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }
}
