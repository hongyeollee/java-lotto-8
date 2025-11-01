package lotto.domain.generateNumbers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GenerateFixedNumbersTest {

    @DisplayName("GenerateFixedNumbers 테스트용 지정된 로또 번호 생성 검증")
    @Test
    void 테스트용_지정_로또번호_생성() {
        List<Integer> fixedLottoNumber = List.of(6,2,4,3,1,5);
        assertThat(new GenerateFixedNumbers(fixedLottoNumber).generate()).isEqualTo(List.of(1,2,3,4,5,6));
    }

    @DisplayName("GenerateFixedNumbers 테스트용 지정된 로또 번호가 6개가 아닐때 예외규칙")
    @Test
    void 테스트용_지정_로또번호_7개로_생성_예외규칙() {
        List<Integer> fixedLottoNumber1 = List.of(6,2,4,3,1,5,7);
        assertThatThrownBy(() -> new GenerateFixedNumbers(fixedLottoNumber1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");

    List<Integer> fixedLottoNumber2 = List.of(6,2,4,3,1);
    assertThatThrownBy(() -> new GenerateFixedNumbers(fixedLottoNumber2))
            .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또 번호는 6개여야 합니다.");
}
}
