package app.model;

import java.util.*;
import java.util.stream.Collectors;

public class LotteryTickets {
    private List<LotteryTicket> lotteryTickets = new ArrayList<>();
    private int numManualTickets = 0;
    private int numAutoTickets = 0;

    public void appendAuto(Money money) {
        while(money.getMoney() >= Money.getTicketPrice()) {
            lotteryTickets.add(new LotteryTicket(LotteryNumbers.auto()));
            money.buyTicket();
            numAutoTickets += 1;
        }
    }

    public void appendManual(Order order) {
        while(order.getToBuy() > 0) {
            lotteryTickets.add(new LotteryTicket(order.popLotteryNumbers()));
            order.buyTicket();
            numManualTickets += 1;
        }
    }

    public List<List<Integer>> getNumbersList() {
        return lotteryTickets.stream()
                .map(LotteryTicket::getNumbers)
                .collect(Collectors.toList());
    }

    public int getNumManualTickets() {
        return numManualTickets;
    }

    public int getNumAutoTickets() {
        return numAutoTickets;
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
