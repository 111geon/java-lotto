package app.model;

import java.util.List;
import java.util.stream.Collectors;

public class LotteryTicket {
    private final LotteryNumbers lotteryNumbers;

    public LotteryTicket(LotteryNumbers lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    List<Integer> getNumbers() {
        return lotteryNumbers.getLotteryNumbers().stream()
                .map(LotteryNumber::getNumber)
                .collect(Collectors.toList());
    }

    Winner getWinner(WinningNumbers winningNumbers) {
        int numMatchBase = winningNumbers.numMatchBase(lotteryNumbers);
        boolean hasBonus = winningNumbers.hasBonus(lotteryNumbers);
        return Winner.findWinner(numMatchBase, hasBonus);
    }
}
