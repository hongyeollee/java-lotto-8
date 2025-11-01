package lotto.serivce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.generateNumbers.GenerateLottoNumbers;
import lotto.util.Validate;

public class LottoService {
    private final GenerateLottoNumbers generateLottoNumbers;

    public LottoService(GenerateLottoNumbers generateLottoNumbers) {
        this.generateLottoNumbers = generateLottoNumbers;
    }

    public List<Lotto> purchase(String amount) {
        int toIntAmount = Validate.purchaseValidateAndToIntAmount(amount);
        List<Lotto> lottoGames = new ArrayList<>();
        int count = toIntAmount / Validate.PRICE_UNIT;

        for (int i = 0;  i < count; i++) {
            lottoGames.add(new Lotto(generateLottoNumbers.generate()));
        }
        return lottoGames;
    }

    public Statistics evaluate(List<Lotto> lottos, List<Integer> winning, int bonusNumber) {
        Map<Rank, Integer> rankCounts = initCounts();
        long totalReward = 0;

        for(Lotto lotto : lottos) {
            int match = lotto.matchCount(winning);
            boolean isBonus = match == 5 && lotto.hasNumber(bonusNumber);
            Rank rank = Rank.lottoRank(match, isBonus);
            rankCounts.put(rank, rankCounts.get(rank) + 1);
            totalReward += rank.getRewardMoney();
        }
        int spendAmount = lottos.size() * Validate.PRICE_UNIT;
        double percent = calculatePercent(totalReward, spendAmount);
        return new Statistics(rankCounts, percent);
    }

    private Map<Rank, Integer> initCounts() {
        Map<Rank, Integer> map = new EnumMap<>(Rank.class);
        for (Rank rank : Rank.values()) map.put(rank, 0);
        return map;
    }

    private double calculatePercent(long totalReward, int spendAmount) {
        if (spendAmount == 0) return 0.0;

        double percent = (double) totalReward / spendAmount * 100;
        return Math.round(percent * 10) / 10.0;
    }

    public static class Statistics {
        private final Map<Rank, Integer> rankCounts;
        private final double percent;

        public Statistics(Map<Rank, Integer> rankCount, double percent) {
            this.rankCounts = Collections.unmodifiableMap(rankCount);
            this.percent = percent;
        }

        public int countOf(Rank rank) { return rankCounts.getOrDefault(rank, 0); }
        public double getPercent() { return percent; }
    }

}
