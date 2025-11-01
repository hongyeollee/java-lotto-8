package lotto.domain.generateNumbers;

import java.util.List;

public class GenerateFixedNumbers implements GenerateLottoNumbers{
    private final List<Integer> numbers;

    public GenerateFixedNumbers(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        this.numbers = List.copyOf(numbers);
    }

    @Override
    public List<Integer> generate() {
        return numbers.stream().sorted().toList();
    }
}
