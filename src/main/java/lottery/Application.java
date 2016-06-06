package lottery;

import java.util.Scanner;
import lottery.service.lottery.LotteryService;

/**
 * Application class
 *
 * @author Tong Zhou
 */
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
        Scanner scanner = new Scanner(System.in);
        lotteryService.run(scanner);
        scanner.close();
       
    }
}
