package lottery.service.lottery;

public class Rules 
{
    /**
     * {@value #INIT_POT}
     */
    public final static int INIT_POT            = 200;

    /**
     * {@value #MAX_PARTICIPANTS}
     */
    public final static int MAX_PARTICIPANTS    = 50;

    /**
     * {@value #MIN_PARTICIPANTS}
     */
    public final static int MIN_PARTICIPANTS    = 3;

    /**
     * {@value #NUMBER_OF_WINNERS}
     */
    public final static int NUMBER_OF_WINNERS   = 3;

    /**
     * {@value #PRICE_FOR_EACH_BALL}
     */
    public final static int PRICE_FOR_EACH_BALL = 10;

    /**
     * {@value #PRIZE_RATE}
     */   
    public final static double[] PRIZE_RATE = new double[] { 0.75, 0.15, 0.10 };
}
