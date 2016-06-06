package lottery.entity;

import org.junit.After;
import org.junit.Before;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

import org.junit.Test;


public class DrawTest
{
    private static final double DELTA = 1e-15; 
    private Draw draw;

    @Before
    public void setUp() 
    {
        List<Participant> participants = new ArrayList<Participant>();
        List<Winner> winners           = new ArrayList<Winner>();
        this.draw = new Draw(100, 10.99, participants, winners);
    }

    @Test    
    public void testGetSetPrice() 
    {
        assertEquals(draw.getPrice(), 10.99, DELTA);
        draw.setPrice(5.99);
        assertEquals(draw.getPrice(), 5.99, DELTA);
    }

    @Test    
    public void testGetSetParticipant() 
    {
        assertThat(draw.getParticipants(), is(Arrays.asList()));
        draw.setParticipants(Arrays.asList(new Participant("Tom", 1), new Participant("Chris", 2)));
        assertThat(
            draw.getParticipants(),
            containsInAnyOrder(
                allOf(hasProperty("firstName", is("Tom")), hasProperty("ballNumber", is(1))),
                allOf(hasProperty("firstName", is("Chris")), hasProperty("ballNumber", is(2)))
            )
        );
    }

    @Test    
    public void testGetSetWinner() 
    {
        assertThat(draw.getWinners(), is(Arrays.asList()));
        draw.setWinners(Arrays.asList(
            new Winner(new Participant("Tom", 1), 10.99), 
            new Winner(new Participant("Chris", 2), 8.88)
        ));
        assertThat(
            draw.getWinners(),
            containsInAnyOrder(
                allOf(hasProperty("firstName", is("Tom")), hasProperty("ballNumber", is(1)), hasProperty("prize", is(10.99))),
                allOf(hasProperty("firstName", is("Chris")), hasProperty("ballNumber", is(2)), hasProperty("prize", is(8.88)))
            )
        );
    }
}
