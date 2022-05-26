package app.model;

import java.util.ArrayList;
import java.util.List;

public class LotteryTickets {
    private List<LotteryTicket> lotteryTickets = new ArrayList<>();

    public void generateTickets(int money) {
        for(int i=money / LotteryTicketConst.PRICE.getValue(); i>0; i--) {
            LotteryTicketNumbers lotteryTicketNumbers = new LotteryTicketNumbers();
            lotteryTickets.add(new LotteryTicket(lotteryTicketNumbers));
        }
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
}
