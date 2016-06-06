package lottery.service.lottery;

import lottery.entity.Winner;
import lottery.exception.WinnerNotDrawnException;
import static java.util.stream.Collectors.*;
import java.util.stream.IntStream;
import java.util.List;

/**
 * Output Class
 *
 * Description: Handle output string
 *
 * @author Tong Zhou
 */
public class Output 
{
    /**
     * {@value #WINNNER_DRAW_FINISHED}
     */
    public static final String WINNNER_DRAW_FINISHED = "Winnber has been drawn";

    /**
     * {@value #NEW_DRAW_START}
     */
    public static final String NEW_DRAW_START        = "New draw has been start";

    /**
     * {@value #COMMAND_WAITING}
     */
    public static final String COMMAND_WAITING       = "Please enter commands [purchase, draw, winners, restart, quit]: ";


    /**
     * {@value #COMMAND_WAITING_NAME}
     */
    public static final String COMMAND_WAITING_NAME  = "Please enter first name: ";

    /**
     * get Display Purchase
     *
     * @param int ballNumber
     * @return String
     */
    public String getDisplayPurchase(int ballNumber) 
    {
        return "Thanks for you purchase, your ball number is: " + ballNumber;
    }

    /**
     * Return Display winners string
     *
     * @param List<Winner> winners
     * @return String
     *
     * @throws WinnerNotDrawnException
     */
    public String getDisplayWinners(List<Winner> winners) throws WinnerNotDrawnException
    {
        if (winners.size() == 0) {
            throw new WinnerNotDrawnException("Lottery has not been drawn, no winner found");
        }

        return winnersHeaderFormat(winners.size()) + "\n\n" + winnersFormat(winners);
    }


    /**
     * Return winners header display string
     *
     * @param int winnerSize
     * @return String
     */
    private String winnersHeaderFormat(int winnerSize) 
    {
        return IntStream.range(1, winnerSize + 1)
            .mapToObj(index -> ordinal(index) + " ball")
            .collect(joining(" "));
    }

    /**
     * Return winners format
     *
     * @param List<Winner> winners
     * @return String
     */
    private String winnersFormat(List<Winner> winners)
    {
        return winners.stream()
            .map(oneWinner -> oneWinner.getFirstName() + ": " + oneWinner.getPrize() + "$")
            .collect(joining(" "));
    }

    /**
     * Covert int number to string with ordinal value
     *
     * @param int number
     * @return String
     */
    protected String ordinal(int number) 
    {
        String[] sufixes = new String[] { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th" };
        switch (number % 100) {
        case 11:
        case 12:
        case 13:
            return number + "th";
        default:
            return number + sufixes[number % 10];
        }
    }

}
