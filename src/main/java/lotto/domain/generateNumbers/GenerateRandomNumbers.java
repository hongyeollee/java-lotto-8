package lotto.domain.generateNumbers;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenerateRandomNumbers implements GenerateLottoNumbers{
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private static final int LOTTO_MAX_COUNT = 6;

    @Override
    public List<Integer> generate() {
        List<Integer> game = Randoms.pickUniqueNumbersInRange(MIN_NUMBER, MAX_NUMBER, LOTTO_MAX_COUNT);
        List<Integer> copy = new ArrayList<>(game);
        Collections.sort(copy);
        return copy;
    }
}
