package app.model;

import java.util.*;
import java.util.stream.Collectors;

public class LotteryTickets {
    private List<LotteryTicket> lotteryTickets = new ArrayList<>();

    public LotteryTickets(Money money) {
        for(int i = money.getMoney() / Money.getTicketPrice(); i > 0; i--) {
            lotteryTickets.add(new LotteryTicket(LotteryNumbers.auto()));
        }
    }

    public LotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public int size() {
        return lotteryTickets.size();
    }

    public List<List<Integer>> getNumbersList() {
        return lotteryTickets.stream()
                .map(LotteryTicket::getNumbers)
                .collect(Collectors.toList());
    }

    public Winners getWinnersFrom(WinningNumbers winningNumbers) {
        Map<Winner, Integer> winners = new LinkedHashMap<>();

        for(Winner winner: Winner.values()) {
            winners.put(winner, 0);
        }

        for(LotteryTicket lotteryTicket: lotteryTickets) {
            Winner winner = lotteryTicket.getWinner(winningNumbers);
            winners.put(winner, winners.get(winner)+1);
        }

        return new Winners(winners);
    }
}
