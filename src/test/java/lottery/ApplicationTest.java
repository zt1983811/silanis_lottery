package lottery;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import lottery.service.lottery.Output;

public class ApplicationTest
{
    private final ByteArrayOutputStream outContent =  new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent =  new ByteArrayOutputStream();

    @Before
    public void setUp() 
    {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @After
    public void cleanUpStreams() 
    {
        System.setOut(null);
        System.setErr(null);
    }
    @Test    
    public void testOutputText() 
    {
        try {

            ByteArrayInputStream in = new ByteArrayInputStream("quit\r\n".getBytes());
            System.setIn(in);
            Application.main(new String[]{});
            assertEquals(Output.COMMAND_WAITING, outContent.toString());
            System.setIn(in);

        } catch (AssertionError e) {
            fail("Test failed by exception " + e.getMessage()); 
        }
    }
}
