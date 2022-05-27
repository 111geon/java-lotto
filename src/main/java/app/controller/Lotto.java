package app.controller;

import app.model.LotteryTickets;
import app.model.Winners;
import app.view.Receiver;
import app.view.Viewer;

import java.util.List;

public class Lotto {
    private final LotteryTickets lotteryTickets;

    public Lotto(LotteryTickets lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public void play() {
        setLotto();
        doLotto();
    }

    private void setLotto() {
        int money = Receiver.askMoney();
        lotteryTickets.setLotteryTickets(money);
        Viewer.printLotteryTickets(lotteryTickets);
    }

    private void doLotto() {
        List<Integer> winningNumbers = Receiver.askWinningNumbers();
        int bonusNumber = Receiver.askBonusNumber(winningNumbers);
        Winners winners = lotteryTickets.getWinners(winningNumbers, bonusNumber);
        Viewer.printStatistics(winners, winners.calculateProfit());
    }
}
