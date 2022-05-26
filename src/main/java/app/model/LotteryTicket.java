package app.model;

import java.util.List;

public class LotteryTicket {
    private final LotteryTicketNumbers lotteryTicketNumbers;

    public LotteryTicket(LotteryTicketNumbers lotteryTicketNumbers) {
        this.lotteryTicketNumbers = lotteryTicketNumbers;
    }

    List<Integer> getNumberList() {
        return lotteryTicketNumbers.getNumbers();
    }
}
