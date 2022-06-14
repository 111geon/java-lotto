package app.model;

public class WinningNumbers {
    private final LotteryNumbers winningNumbersBase;
    private final LotteryNumber bonusNumber;

    public WinningNumbers(LotteryNumbers winningNumbersBase, LotteryNumber bonusNumber) {
        validate(winningNumbersBase, bonusNumber);
        this.winningNumbersBase = winningNumbersBase;
        this.bonusNumber = bonusNumber;
    }

    int numMatchBase(LotteryNumbers lotteryNumbers) {
        return (int) winningNumbersBase.getLotteryNumbers().stream()
                .filter(lotteryNumbers::contains)
                .count();
    }

    boolean hasBonus(LotteryNumbers lotteryNumbers) {
        return lotteryNumbers.contains(bonusNumber);
    }

    private void validate(LotteryNumbers winningNumbersBase, LotteryNumber bonusNumber) {
        if(winningNumbersBase.contains(bonusNumber)) {
            throw new WinningNumbersException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static class WinningNumbersException extends IllegalArgumentException {
        public WinningNumbersException(String message) {
            super(message);
        }
    }
}
