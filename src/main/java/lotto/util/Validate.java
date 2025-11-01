package lotto.util;

import java.util.HashSet;
import java.util.List;

public class Validate {
    public static final int PRICE_UNIT = 1000;

    static void validateNumberRange(int number) {
        if (number < 1 || number > 45) throw new IllegalArgumentException("[ERROR] 번호는 1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    public static void validateNumbersRange(List<Integer> numbers) {
        for (Integer number : numbers) {
            validateNumberRange(number);
        }
    }

    public static void validateDuplicateNumber(List<Integer> numbers) {
        if (new HashSet<>(numbers).size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또번호에 중복되는 번호가 존재합니다.");
        }
    }

    public static int purchaseValidateAndToIntAmount(String amount) {
        int toIntAmount;
        try {
            toIntAmount = Parse.parseAmount(amount);
        } catch (NumberFormatException | NullPointerException e ) {
            throw new IllegalArgumentException("[ERROR] 올바른 금액을 입력해 주세요.");
        }
        if (toIntAmount <= 0 ) throw new IllegalArgumentException("[ERROR] 구매 금액은 0보다 커야합니다.");
        if (toIntAmount % PRICE_UNIT !=0 ) throw new IllegalArgumentException("[ERROR] 1,000원 단위로 구매가능 합니다.");
        return toIntAmount;
    }

    public static void validateDuplicatedBonusNumber(List<Integer> winningNumbers , int number) {
        validateNumberRange(number);
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호가 당첨번호에 존재합니다.");
        }
    }
}
