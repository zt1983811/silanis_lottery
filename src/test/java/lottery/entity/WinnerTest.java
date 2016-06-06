package lottery.entity;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class WinnerTest
{
    private static final double DELTA = 1e-15; 
    private Winner winner;

    @Before
    public void setUp() 
    {
        Participant participant = new Participant("Chris", 1);
        this.winner             = new Winner(participant, 2.99);
    }

    @Test    
    public void testGetSetPrize() 
    {
        assertEquals(winner.getPrize(), 2.99, DELTA);
        winner.setPrize(5.99);
        assertEquals(winner.getPrize(), 5.99, DELTA);
    }

}
