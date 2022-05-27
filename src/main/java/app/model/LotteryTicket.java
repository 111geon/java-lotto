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

    Winner getWinner(List<Integer> winningNumbers, int bonusNumber) {
        int numWinningNumbers = 0;

        for(Integer winningNumber: winningNumbers) {
            numWinningNumbers = increaseNum(numWinningNumbers, lotteryTicketNumbers.containNumber(winningNumber));
        }

        boolean hasBonus = lotteryTicketNumbers.containNumber(bonusNumber);
        return Winner.getWinner(numWinningNumbers, hasBonus);
    }

    private int increaseNum(int num, boolean isTrue) {
        if(isTrue) {
            num += 1;
        }
        return num;
    }
}
