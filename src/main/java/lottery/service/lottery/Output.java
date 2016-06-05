package lottery.service.lottery;

import lottery.entity.Winner;
import lottery.exception.WinnerNotDrawnException;

import static java.util.stream.Collectors.*;

import java.util.stream.IntStream;
import java.util.List;

public class Output 
{
    public static final String WINNBER_DRAW_FINISHED = "Winnber has been drawn";
    public static final String NEW_DRAW_START        = "New draw has been start";
    public static final String COMMAND_WAITING       = "Please enter commands [purchase, draw, winners, restart, quit]: ";
    public static final String COMMAND_WAITING_NAME  = "Please enter first name: ";

    public String getDisplayPurchase(int ballNumber) 
    {
        return "Thanks for you purchase, your ball number is: " + ballNumber;
    }

    public String getDisplayWinners(List<Winner> winners) throws WinnerNotDrawnException
    {
        if (winners.size() == 0) {
            throw new WinnerNotDrawnException("Lottery has not been drawn, no winner found");
        }

        return winnersHeaderFormat(winners.size()) + "\n\n" + winnersFormat(winners);
    }


    private String winnersHeaderFormat(int winnberSize) 
    {
        return IntStream.range(1, winnberSize + 1)
            .mapToObj(index -> ordinal(index) + " ball")
            .collect(joining(" "));
    }

    private String winnersFormat(List<Winner> winners)
    {
        return winners.stream()
            .map(oneWinner -> oneWinner.getFirstName() + ": " + oneWinner.getPrize() + "$")
            .collect(joining(" "));
    }

    private String ordinal(int number) 
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
