package lotto.util;

import java.util.HashSet;
import java.util.List;

public class Validate {

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
}
