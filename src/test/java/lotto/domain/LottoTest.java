package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LottoTest {
    @Test
    void 로또_번호의_개수가_6개가_넘어가면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복된 숫자가 있으면 예외가 발생한다.")
    @Test
    void 로또_번호에_중복된_숫자가_있으면_예외가_발생한다() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @DisplayName("getLottoFormatted 로또번호 배열숫자형 타입 toString 반환")
    @Test
    void 로또_toStirng으로_포맷팅() {
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        Lotto lotto = new Lotto(numbers);
        String method = lotto.getLottoFormatted();

        assertThat(method).isEqualTo("[1, 2, 3, 4, 5, 6]");
    }

    @DisplayName("matchCount 당첨번호 매칭 개수 반환")
    @Test
    void 로또_당첨번호와_부여받은_로또번호_매칭_개수_반환() {
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        Lotto lotto = new Lotto(numbers);
        int matchCount = lotto.matchCount(List.of(2,5,9,11,30,40));
        assertThat(matchCount).isEqualTo(2);
    }

    @DisplayName("hasNumber 로또 당첨번호에 보너스 번호 여부 반환")
    @Test
    void 번호_리스트에_번호_포함_여부_반환() {
        Lotto lotto =new Lotto(List.of(1,2,3,4,5,6));
        boolean hasNumber = lotto.hasNumber(2);
        assertThat(hasNumber).isEqualTo(true);

        boolean hasNotNumber = lotto.hasNumber(10);
        assertThat(hasNotNumber).isEqualTo(false);
    }
}
