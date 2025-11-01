package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.serivce.LottoService;
import lotto.util.Parse;
import lotto.util.Validate;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;
    private final LottoService lottoService;

    public LottoController(InputView inputView, OutputView outputView, LottoService lottoService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoService = lottoService;
    }

    public void run() {
        String amount = inputView.purchase();

        List<Lotto> games = lottoService.purchase(amount);
        outputView.printGames(games);

        String winning = inputView.winningNumber();
        List<Integer> winningNumbers = Parse.parseWinningNumbers(winning);

        String bonusNumber = inputView.bonusNumber();
        int parseBonusNumber = Integer.parseInt(bonusNumber);
        Validate.validateDuplicatedBonusNumber(winningNumbers, parseBonusNumber);

        var stats = lottoService.evaluate(games, winningNumbers, parseBonusNumber);
        outputView.printStatistics(stats);
        outputView.printProfitPercent(stats.getPercent());
    }
}