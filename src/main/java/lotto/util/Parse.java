package lotto.util;

import java.util.ArrayList;
import java.util.List;

public class Parse {

    public static int parseAmount(String amount) {
        return Integer.parseInt(amount.replaceAll("[,\\s]", "").trim());
    }
}
