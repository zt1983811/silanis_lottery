/* file name  : Participant.java
 * authors    : 
 * created    : Sat 3 Jun 2015 12:40:14 PM EDT
 * copyright  : 
 *
 * modifications:
 *
 */
package lottery.entity;

/** 
 * Participant entity 
 *
 * @author: Tong Zhou
 * @version 1.0.0
 */
public class Participant
{
    /** 
     * Participant first name 
     */
    private String firstName;

    /** 
     * Participant ball number
     */
    private int ballNumber;

    /**
     * Constructor
     *
     * @param firstName
     * @param ballNumber
     */
    public Participant(String firstName, int ballNumber) 
    {
        this.firstName  = firstName;
        this.ballNumber = ballNumber;
    }

    /**
     * Constructor
     *
     * @param firstName
     * @param ballNumber
     */
    public Participant(Participant participant) 
    {
        this.firstName = participant.getFirstName();
        this.ballNumber = participant.getBallNumber();
    }

    /**
     * @return the firstName
     */
    public String getFirstName() 
    {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) 
    {
        this.firstName = firstName;
    }

    /**
     * @return the ballNumber
     */
    public int getBallNumber() 
    {
        return ballNumber;
    }

    /**
     * @param ballNumber the ballNumber to set
     */
    public void setBallNumber(final int ballNumber) 
    {
        this.ballNumber = ballNumber;
    }
} 
