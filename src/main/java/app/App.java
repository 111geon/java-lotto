package app;

import app.controller.Lotto;
import app.model.LotteryTickets;

public class App {
    public static void main(String[] args) {
        LotteryTickets lotteryTickets = new LotteryTickets();
        Lotto lotto = new Lotto(lotteryTickets);
        lotto.play();
    }
}
