package app.model;

import java.util.Arrays;

public enum Winner {
    FIFTH(3, false, 5000) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return numWinningNumbers == getNumWinningNumbers();
        }
    },
    FOURTH(4, false, 50000) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return numWinningNumbers == getNumWinningNumbers();
        }
    },
    THIRD(5, false, 1500000) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return numWinningNumbers == getNumWinningNumbers() && hasBonus == getNeedBonus();
        }
    },
    SECOND(5, true, 30000000) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return numWinningNumbers == getNumWinningNumbers() && hasBonus == getNeedBonus();
        }
    },
    FIRST(6, false, 2000000000) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return numWinningNumbers == getNumWinningNumbers();
        }
    },
    NOGO(0, false, 0) {
        @Override
        public boolean check(int numWinningNumbers, boolean hasBonus) {
            return false;
        }
    };

    private final int numWinningNumbers;
    private final boolean needBonus;
    private final int prizeMoney;

    Winner(int numWinningNumbers, boolean needBonus, int prizeMoney) {
        this.numWinningNumbers = numWinningNumbers;
        this.needBonus = needBonus;
        this.prizeMoney = prizeMoney;
    }

    abstract public boolean check(int numWinningNumbers, boolean hasBonus);

    public int getNumWinningNumbers() {
        return numWinningNumbers;
    }

    public boolean getNeedBonus() {
        return needBonus;
    }

    public int getPrizeMoney() {
        return prizeMoney;
    }


    static Winner findWinner(int numWinningNumbers, boolean hasBonus) {
        return Arrays.stream(Winner.values())
                .filter(winner -> winner.check(numWinningNumbers, hasBonus))
                .findAny()
                .orElse(NOGO);
    }
}
