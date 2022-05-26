package app.controller;

import app.model.LotteryTickets;
import app.view.Receiver;
import app.view.Viewer;

import java.util.List;

public class Lotto {
    private final LotteryTickets lotteryTickets;

    public Lotto(LotteryTickets lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public void play() {
        int money = Receiver.askMoney();
        lotteryTickets.generateTickets(money);
        Viewer.printLotteryTickets(lotteryTickets);
        List<Integer> winningNumbers = Receiver.askWinningNumbers();
        int bonusNumber = Receiver.askBonusNumber(winningNumbers);
        System.out.println(winningNumbers);
    }
}
