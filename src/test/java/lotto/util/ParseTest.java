package lotto.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ParseTest {

    @DisplayName("parseAmount 공백 제거")
    @Test
    void 구매금액_공백시_쉼표나_공백이_제거된다() {
        assertThat(Parse.parseAmount(" 1000")).isEqualTo(1000);
        assertThat(Parse.parseAmount("45, 000")).isEqualTo(45000);
    }

    @DisplayName("parseWinningNumbers 쉼표기준으로 공백제거 및 숫자 배열형으로 파싱")
    @Test
    void 당첨번호_문자열에서_숫자_배열형으로_파싱() {
        assertThat(Parse.parseWinningNumbers("1,2,3, 4,5 ")).isEqualTo(List.of(1,2,3,4,5));
    }
}
