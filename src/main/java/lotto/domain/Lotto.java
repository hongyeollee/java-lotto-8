package lotto.domain;

import java.util.List;
import lotto.util.Validate;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        Validate.validateNumbersRange(numbers);
        Validate.validateDuplicateNumber(numbers);
    }

    // TODO: 추가 기능 구현
    public String getLottoFormatted() {
        return numbers.toString();
    }

    public int matchCount(List<Integer> winning) {
        int count = 0;
        for (int number : numbers) if (winning.contains(number)) count++;
        return count;
    }

    public boolean hasNumber(int number) {
        return numbers.contains(number);
    }

    //테스트코드용 메소드
    public List<Integer> getNumbers() {
        return numbers;
    }
}
