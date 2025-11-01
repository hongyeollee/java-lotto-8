package lotto.view;

import java.text.DecimalFormat;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.Rank;
import lotto.serivce.LottoService.Statistics;

public class OutputView {

    public void printGames(List<Lotto> games) {
        System.out.println();
        System.out.println(games.size() + "개를 구매했습니다.");
        for (Lotto game : games) {
            System.out.println(game.getLottoFormatted());
        }
    }

    public void printStatistics(Statistics stats) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        printLine(stats.countOf(Rank.FIFTH), 3, "5,000원");
        printLine(stats.countOf(Rank.FOURTH), 4, "50,000원");
        printLine(stats.countOf(Rank.THIRD), 5, "1,500,000원");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + stats.countOf(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + stats.countOf(Rank.FIRST) + "개");
    }

    private void printLine(int count, int match, String money) {
        System.out.println(match + "개 일치 (" + money + ") - " + count + "개");
    }


    public void printProfitPercent(double number) {
        DecimalFormat formatted = new DecimalFormat("0.0");
        System.out.println("총 수익률은 " + formatted.format(number) + "%입니다.");
    }
}
