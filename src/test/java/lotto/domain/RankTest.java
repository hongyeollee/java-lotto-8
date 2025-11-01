package lotto.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RankTest {

    @DisplayName("당첨 번호에 따른 로또 순위에 대한 정보 검증")
    @Test
    void 로또_랭킹() {
        assertThat(Rank.lottoRank(1, true)).isEqualTo(Rank.MATCH_ONE);
        assertThat(Rank.lottoRank(1, false)).isEqualTo(Rank.MATCH_ONE);
        assertThat(Rank.lottoRank(2, true)).isEqualTo(Rank.MATCH_TWO);
        assertThat(Rank.lottoRank(2, false)).isEqualTo(Rank.MATCH_TWO);
        assertThat(Rank.lottoRank(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.lottoRank(3, false)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.lottoRank(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.lottoRank(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.lottoRank(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.lottoRank(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.lottoRank(6, true)).isEqualTo(Rank.FIRST);
        assertThat(Rank.lottoRank(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.lottoRank(0, true)).isEqualTo(Rank.NONE);
        assertThat(Rank.lottoRank(0, false)).isEqualTo(Rank.NONE);
    }

    @DisplayName("당첨 번호에 따른 당첨금 반환 검증")
    @Test
    void 로또_당첨금_반환() {
        Rank rank = Rank.lottoRank(3,false);
        assertThat(rank.getRewardMoney()).isEqualTo(5000);
    }
}
