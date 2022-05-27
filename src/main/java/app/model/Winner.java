package app.model;

public enum Winner {
    FIFTH(3, false, 5000),
    FOURTH(4, false, 50000),
    THIRD(5, false, 1500000),
    SECOND(5, true, 30000000),
    FIRST(6, false, 2000000000),
    NOGO(0, false, 0);

    private final int numWinningNumbers;
    private final boolean needBonus;
    private final int prizeMoney;

    Winner(int numWinningNumbers, boolean needBonus, int prizeMoney) {
        this.numWinningNumbers = numWinningNumbers;
        this.needBonus = needBonus;
        this.prizeMoney = prizeMoney;
    }

    public int getNumWinningNumbers() {
        return numWinningNumbers;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }

    public static Winner getWinner(int numWinningNumbers, boolean hasBonus) {
        if(numWinningNumbers == FIRST.numWinningNumbers) { return FIRST; }
        if(numWinningNumbers == SECOND.numWinningNumbers && hasBonus == SECOND.needBonus) { return SECOND; }
        if(numWinningNumbers == THIRD.numWinningNumbers) { return THIRD; }
        if(numWinningNumbers == FOURTH.numWinningNumbers) { return FOURTH; }
        if(numWinningNumbers == FIFTH.numWinningNumbers) { return FIFTH; }
        return NOGO;
    }
}
