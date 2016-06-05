/* file name  : Winner.java
 * authors    : 
 * created    : Sat 3 Jun 2015 12:40:14 PM EDT
 * copyright  : 
 *
 * modifications:
 *
 */
package lottery.entity;

/** 
 * Winner entity 
 *
 * @author: Tong Zhou
 * @version 1.0.0
 */
public class Winner extends Participant
{
    /** 
     * Winner prize
     */
    private double prize;

    /**
     * Constructor
     *
     * @param participant
     * @param prize
     */
    public Winner(Participant participant, double prize) 
    {
        super(participant);
        this.prize = prize;
    }

    /**
     * @return the prize
     */
    public double getPrize() {
        return prize;
    }

    /**
     * @param prize the prize to set
     */
    public void setPrize(double prize) {
        this.prize = prize;
    }

}
