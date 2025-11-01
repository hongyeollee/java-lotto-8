package lotto.service;



import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.domain.generateNumbers.GenerateLottoNumbers;
import lotto.serivce.LottoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LottoServiceTest {

    //테스트용 고정 로또번호 생성 클래스
    static class FixedSequenceGenerator implements GenerateLottoNumbers {
        private final List<List<Integer>> sequences;
        private int index = 0;

        public FixedSequenceGenerator(List<List<Integer>> sequences) {
            this.sequences = sequences;
        }

        @Override
        public List<Integer> generate() {
            if (index >= sequences.size()) {
                throw new IllegalStateException("생성 가능한 로또 번호 세트가 부족합니다.");
            }
            List<Integer> result = sequences.get(index);
            index++;
            return result;
        }
    }

    @DisplayName("purchase 3천원의 금액으로 로또 3장을 구매하여 3게임의 로또를 생성 검증")
    @Test
    void 금액만큼_로또를_구입한다() {
        List<List<Integer>> fixedNumbersOf3Games = new ArrayList<>();
        fixedNumbersOf3Games.add(List.of(1, 2, 3, 4, 5, 6));
        fixedNumbersOf3Games.add( List.of(7, 8, 9, 10, 11, 12));
        fixedNumbersOf3Games.add(List.of(13, 14, 15, 16, 17, 18));

        FixedSequenceGenerator generator = new FixedSequenceGenerator(fixedNumbersOf3Games);
        String amount = "3000";
        LottoService lottoService = new LottoService(generator);
        List<Lotto> purchaseLottos = lottoService.purchase(amount);

        assertThat(purchaseLottos).hasSize(3);
        assertThat(purchaseLottos.getFirst().getNumbers()).containsExactly(1,2,3,4,5,6);
        assertThat(purchaseLottos.get(1).getNumbers()).containsExactly(7,8,9,10,11,12);
        assertThat(purchaseLottos.getLast().getNumbers()).containsExactly(13, 14, 15, 16, 17, 18);

    }

    @DisplayName("evaluate 구매한 로또를 당첨 로또와 비교하여 평가한다.")
    @Test
    void 구매한_로또를_당첨_로또와_비교하여_평가() {
        // given: 당첨번호/보너스
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;

        // 등수별로 1장씩 구성:
        // FIRST  : 6개 일치
        // SECOND : 5개 + 보너스
        // THIRD  : 5개 (보너스X)
        // FOURTH : 4개
        // FIFTH  : 3개
        // MISS   : 2개 이하
        List<Lotto> lottos = List.of(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)),      // FIRST
                new Lotto(List.of(1, 2, 3, 4, 5, 7)),      // SECOND (5 + bonus)
                new Lotto(List.of(1, 2, 3, 4, 5, 8)),      // THIRD (5, no bonus)
                new Lotto(List.of(1, 2, 3, 4, 9, 10)),     // FOURTH (4)
                new Lotto(List.of(1, 2, 3, 11, 12, 13)),   // FIFTH (3)
                new Lotto(List.of(1, 2, 14, 15, 16, 17))   // MISS (2)
        );

        LottoService service = new LottoService(new FixedSequenceGenerator(List.of())); // generator 미사용

        LottoService.Statistics stats = service.evaluate(lottos, winning, bonus);

        // then: 각 랭크 1개씩 카운트
        assertThat(stats.countOf(Rank.FIRST)).isEqualTo(1);
        assertThat(stats.countOf(Rank.SECOND)).isEqualTo(1);
        assertThat(stats.countOf(Rank.THIRD)).isEqualTo(1);
        assertThat(stats.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(stats.countOf(Rank.FIFTH)).isEqualTo(1);

        // 수익률은 Rank 보상금에 의존하므로 여기서는 검증하지 않음
    }

    @Test
    void 당첨_로또가_없다면_수익률은_0이다() {
        List<Integer> winning = List.of(1,2,3,4,5,6);
        int bonusNumber = 7;

        List<Lotto> lottos = List.of(
                new Lotto(List.of(7,8,9,10,11,1)),
                new Lotto(List.of(20,21,22,23,24,1)),
                new Lotto(List.of(30,31,32,33,34,35))
        );

        LottoService lottoService = new LottoService(new FixedSequenceGenerator(List.of()));

        LottoService.Statistics stats = lottoService.evaluate(lottos,winning,bonusNumber);

        assertThat(stats.countOf(Rank.NONE)).isEqualTo(1);
        assertThat(stats.countOf(Rank.MATCH_ONE)).isEqualTo(2);
        assertThat(stats.getPercent()).isEqualTo(0);
    }
}
