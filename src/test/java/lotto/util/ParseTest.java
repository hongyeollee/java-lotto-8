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
}
