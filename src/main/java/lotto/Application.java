package lotto;

import lotto.controller.LottoController;
import lotto.domain.generateNumbers.GenerateRandomNumbers;
import lotto.serivce.LottoService;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoService lottoService = new LottoService(new GenerateRandomNumbers());
        LottoController lottoController = new LottoController(inputView, outputView, lottoService);
        try {
            lottoController.run();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
