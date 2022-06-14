package app.controller;

import app.model.*;
import app.view.Receiver;
import app.view.Viewer;

public class Lotto {
    private final LotteryTickets lotteryTickets;
    private WinningNumbers winningNumbers;

    public Lotto(LotteryTickets lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public void play() {
        setLotteryTickets();
        showLotteryTickets();
        setWinningNumbers();
        showResult();
    }

    private void setLotteryTickets() {
        Money money = Receiver.askMoney();
        Order order = Receiver.askOrder(money);
        lotteryTickets.appendManual(order);
        lotteryTickets.appendAuto(money);
    }

    private void showLotteryTickets() {
        Viewer.printLotteryTickets(
                lotteryTickets.getNumManualTickets(),
                lotteryTickets.getNumAutoTickets(),
                lotteryTickets.getNumbersList()
        );
    }

    private void setWinningNumbers() {
        LotteryNumbers winningNumbersBase = Receiver.askWinningNumbersBase();
        LotteryNumber bonusNumber = Receiver.askBonusNumber(winningNumbersBase);
        this.winningNumbers = new WinningNumbers(winningNumbersBase, bonusNumber);
    }

    private void showResult() {
        Winners winners = lotteryTickets.getWinnersFrom(winningNumbers);
        Viewer.printStatistics(winners.getEntrySet(), winners.calculateProfitRate());
    }
}
