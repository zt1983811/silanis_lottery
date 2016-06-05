package lottery;

import lottery.service.lottery.LotteryService;

public class Application 
{

    /**
     * Main to start application
     * @param <T>
     *
     * @param args
     */
    public static <T> void main(String[] args)
    {
        LotteryService lotteryService = new LotteryService();
        lotteryService.run();
       
    }
}
