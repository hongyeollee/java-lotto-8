package lotto.domain;

import lotto.util.Parse;

public enum Rank {
    FIRST(6, false, "2,000,000,000"),
    SECOND(5, true, "30,000,000"),
    THIRD(5, false, "1,500,000"),
    FOURTH(4, false, "50,000"),
    FIFTH(3, false, "5,000"),
    MATCH_TWO(2, false, "0"),
    MATCH_ONE(1, false, "0"),
    NONE(0,false,"0");


    private final int matchCount;
    private final boolean isBonus;
    private final String rewardMoney;

    Rank(int matchCount, boolean isBonus, String rewardMoney) {
        this.matchCount = matchCount;
        this.isBonus = isBonus;
        this.rewardMoney = rewardMoney;
    }

    public int getRewardMoney() { return Parse.parseAmount(rewardMoney) ; }

    public static Rank lottoRank(int matchCount, boolean isBonus) {
        if (matchCount == 6) return Rank.FIRST;
        if (matchCount == 5 && isBonus) return Rank.SECOND;
        if (matchCount == 5) return Rank.THIRD;
        if (matchCount == 4) return Rank.FOURTH;
        if (matchCount == 3) return Rank.FIFTH;
        if (matchCount == 2) return Rank.MATCH_TWO;
        if (matchCount == 1) return Rank.MATCH_ONE;
        return NONE;
    }
}
