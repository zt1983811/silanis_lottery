package lottery.entity;

import org.junit.Before;

import static org.junit.Assert.assertEquals;
import org.junit.Test;


public class ParticipantTest
{
    private Participant particpant;

    @Before
    public void setUp() 
    {
        this.particpant = new Participant("Chris", 1);
    }

    @Test    
    public void testGetSetFirstName() 
    {
        assertEquals(particpant.getFirstName(), "Chris");
        particpant.setFirstName("Tom");
        assertEquals(particpant.getFirstName(), "Tom");
    }

    @Test    
    public void testGetSetBallNumber() 
    {
        assertEquals(particpant.getBallNumber(), 1);
        particpant.setBallNumber(5);
        assertEquals(particpant.getBallNumber(), 5);
    }

}
