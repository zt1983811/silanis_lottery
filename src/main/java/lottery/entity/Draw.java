/* file name  : Winner.java
 * authors    : 
 * created    : Sat 3 Jun 2015 12:40:14 PM EDT
 * copyright  : 
 *
 * modifications:
 *
 */
package lottery.entity;

import java.util.List;

/** 
 * Draw entity 
 *
 * @author: Tong Zhou
 * @version 1.0.0
 */
public class Draw 
{
    /** 
     * Draw pot
     */
    private int pot;

    /** 
     * Draw price
     */
    private double price;

    /** 
     * Draw participants
     */
    private List<Participant> participants;

    /** 
     * Draw winners
     */
    private List<Winner> winners;

    /**
     * Constructor
     *
     * @param pot
     * @param price
     * @param participants
     * @param winners2
     */
    public Draw(int pot, double price, List<Participant> participants, List<Winner> winners)
    {
        this.pot          = pot;
        this.price        = price;
        this.participants = participants;
        this.winners      = winners;
    }

    /**
     * @return the pot
     */
    public int getPot() 
    {
        return pot;
    }

    /**
     * @param pot the pot to set
     */
    public void setPot(int pot) 
    {
        this.pot = pot;
    }

    /**
     * @return the price
     */
    public double getPrice() 
    {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(double price) 
    {
        this.price = price;
    }

    /**
     * @return the participants
     */
    public List<Participant> getParticipants()
    {
        return participants;
    }

    /**
     * @param participants the participants to set
     */
    public void setParticipants(List<Participant> participants)
    {
        this.participants = participants;
    }

    /**
     * @return the winners
     */
    public List<Winner> getWinners()
    {
        return winners;
    }

    /**
     * @param winners the winners to set
     */
    public void setWinners(List<Winner> winners)
    {
        this.winners = winners;
    }
}
