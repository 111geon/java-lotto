package app.controller;

import app.model.*;
import app.view.Receiver;
import app.view.Viewer;

public class Lotto {
    private LotteryTickets lotteryTickets;
    private WinningNumbers winningNumbers;

    public void play() {
        setLotteryTickets();
        showLotteryTickets();
        setWinningNumbers();
        showResult();
    }

    private void setLotteryTickets() {
        Money money = Receiver.askMoney();
        this.lotteryTickets = new LotteryTickets(money);
    }

    private void showLotteryTickets() {
        Viewer.printLotteryTickets(lotteryTickets);
    }

    private void setWinningNumbers() {
        LotteryNumbers winningNumbersBase = Receiver.askWinningNumbersBase();
        LotteryNumber bonusNumber = Receiver.askBonusNumber(winningNumbersBase);
        this.winningNumbers = new WinningNumbers(winningNumbersBase, bonusNumber);
    }

    private void showResult() {
        Winners winners = lotteryTickets.getWinnersFrom(winningNumbers);
        Viewer.printStatistics(winners);
    }
}
