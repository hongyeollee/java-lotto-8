package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ValidateTest {

    @DisplayName("purchaseValidateAndToIntAmount에 문자타입의 숫자값 구매 검증")
    @Test
    void 구매금액에_문자타입의_숫자값을_입력_구매검증() {
        assertThat(Validate.purchaseValidateAndToIntAmount("10, 000"))
                .isEqualTo(10000);
        assertThat(Validate.purchaseValidateAndToIntAmount("30000"))
                .isEqualTo(30000);
    }

    @DisplayName("purchaseValidateAndToIntAmount에 금액에 잘못된 값이 입력")
    @Test
    void 잘못된_구매금액을_입력했을떄_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.purchaseValidateAndToIntAmount("1000j"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 올바른 금액을 입력해 주세요.");
    }

    @DisplayName("purchaseValidateAndToIntAmount에 금액에 0미만의 값이 입력")
    @Test
    void 구매금액이_0원_이하일때_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.purchaseValidateAndToIntAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 0보다 커야합니다.");
        assertThatThrownBy(() -> Validate.purchaseValidateAndToIntAmount("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 구매 금액은 0보다 커야합니다.");
    }

    @DisplayName("purchaseValidateAndToIntAmount에 금액에 1000원 단위 구매검증")
    @Test
    void 구매금액_1000원_단위로_구매_검증() {
        assertThat(Validate.purchaseValidateAndToIntAmount("10, 000"))
                .isEqualTo(10000);
    }

    @DisplayName("purchaseValidateAndToIntAmount 금액에 1000원 단위가 아닌 경우")
    @Test
    void 구매금액이_1000원_단위로_구매하지_않은_경우_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.purchaseValidateAndToIntAmount("5500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 1,000원 단위로 구매가능 합니다.");
    }

    @DisplayName("validateDuplicateNumber 로또당첨번호에 중복번호 검증")
    @Test
    void 중복되는_번호가_로또_당첨번호에_있는_경우_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.validateDuplicateNumber(List.of(1,2,2,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 로또번호에 중복되는 번호가 존재합니다.");
    }

    @DisplayName("validateDuplicatedBonusNumber 보너스번호 검증")
    @Test
    void 보너스_번호_입력_검증() {
        Validate.validateDuplicatedBonusNumber(List.of(1,2,3,4,5,6), 7);
    }

    @DisplayName("validateDuplicatedBonusNumber 보너스번호 범위 검증")
    @Test
    void 보너스_번호_입력_범위_벗어난_경우() {
        assertThatThrownBy(() -> Validate.validateDuplicatedBonusNumber(List.of(1,2,3,4,5,6), 50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    @DisplayName("validateDuplicatedBonusNumber 보너스번호 당첨번호 중복 검증")
    @Test
    void 보너스_번호가_당첨번호에_이미_있는_경우() {
        assertThatThrownBy(() -> Validate.validateDuplicatedBonusNumber(List.of(1,2,3,4,5,6), 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 보너스 번호가 당첨번호에 존재합니다.");
    }

    @DisplayName("validateNumberRange 로또 당첨번호 범위가 1이상 45이하 범위 벗어남 검증")
    @Test
    void 로또번호가_1이상_45이하_범위를_벗어난_경우_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.validateNumberRange(50))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이의 숫자만 입력 가능합니다.");
        assertThatThrownBy(() -> Validate.validateNumberRange(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이의 숫자만 입력 가능합니다.");
    }

    @DisplayName("validateNumbersRange 로또 당첨번호들이 범위가 1이상 45이하 범위 검증")
    @Test
    void 로또번호들이_1이상_45이하_범위_검증() {
        Validate.validateNumbersRange(List.of(1,2,3,4,5,6));
    }

    @DisplayName("validateNumbersRange 로또 당첨번호들이 범위가 1이상 45이하 범위 벗어난 경우")
    @Test
    void 로또번호들이_1이상_45이하_범위를_벗어난_경우_예외규칙_발생() {
        assertThatThrownBy(() -> Validate.validateNumbersRange(List.of(1,2,88,3,4,5)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 번호는 1부터 45 사이의 숫자만 입력 가능합니다.");
    }
}
