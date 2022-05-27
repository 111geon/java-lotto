package app.model;

import java.util.*;

public class LotteryTickets {
    private List<LotteryTicket> lotteryTickets = new ArrayList<>();

    public void setLotteryTickets(int money) {
        for(int i=money / LotteryTicketConst.PRICE.getValue(); i>0; i--) {
            LotteryTicketNumbers lotteryTicketNumbers = new LotteryTicketNumbers();
            lotteryTickets.add(new LotteryTicket(lotteryTicketNumbers));
        }
    }

    void setLotteryTickets(List<LotteryTicket> lotteryTickets) {
        this.lotteryTickets = lotteryTickets;
    }

    public int size() {
        return lotteryTickets.size();
    }

    public List<List<Integer>> getNumberLists() {
        List<List<Integer>> numberLists = new ArrayList<>();
        for(LotteryTicket lotteryTicket: lotteryTickets) {
            numberLists.add(lotteryTicket.getNumberList());
        }
        return numberLists;
    }

    public Winners getWinners(List<Integer> winningNumbers, int bonusNumber) {
        Map<Winner, Integer> winners = new LinkedHashMap<>();
        for(Winner winner: Winner.values()) {
            winners.put(winner, 0);
        }

        for(LotteryTicket lotteryTicket: lotteryTickets) {
            Winner winner = lotteryTicket.getWinner(winningNumbers, bonusNumber);
            int count = winners.get(winner);
            winners.put(winner, count+1);
        }

        return new Winners(winners);
    }
}
