package app.model;

import java.util.Map;
import java.util.Set;

public class Winners {
    private final Map<Winner, Integer> winners;

    Winners(Map<Winner, Integer> winners) {
        this.winners = winners;
    }

    public Set<Map.Entry<Winner, Integer>> getEntrySet() {
        return winners.entrySet();
    }

    public float calculateProfitRate() {
        int totalProfit = 0;
        int totalInvest = 0;
        for(Map.Entry<Winner, Integer> winnerEntry: winners.entrySet()) {
            totalProfit += winnerEntry.getKey().getPrizeMoney() * winnerEntry.getValue();
            totalInvest += Money.getTicketPrice() * winnerEntry.getValue();
        }
        return totalProfit / (float)totalInvest;
    }
}
