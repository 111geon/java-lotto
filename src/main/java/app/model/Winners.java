package app.model;

import java.util.Map;

public class Winners {
    private final Map<Winner, Integer> winners;

    Winners(Map<Winner, Integer> winners) {
        this.winners = winners;
    }

    public Map<Winner, Integer> getWinners() {
        return winners;
    }

    public float calculateProfit() {
        int totalPrizeMoney = 0;
        int totalTicketMoney = 0;
        for(Map.Entry<Winner, Integer> winner: winners.entrySet()) {
            totalTicketMoney += LotteryTicketConst.PRICE.getValue() * winner.getValue();
            totalPrizeMoney += winner.getKey().getPrizeMoney() * winner.getValue();
        }
        return (float)totalPrizeMoney / (float)totalTicketMoney;
    }
}
