package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class Parse {

    public static int parseAmount(String amount) {
        return Integer.parseInt(amount.replaceAll("[,\\s]", "").trim());
    }

    public static List<Integer> parseWinningNumbers(String winningNumbers) {
        String[] winnings = winningNumbers.split(",");
        List<Integer> numbers = new ArrayList<>();

        for (String winning : winnings) {
            numbers.add(Integer.parseInt(winning.trim()));
        }

        return numbers;
    }
}
