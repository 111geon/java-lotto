package app.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class LotteryNumbers {
    private static final int NUMBERS_LENGTH = 6;
    private final List<LotteryNumber> lotteryNumbers;

    private LotteryNumbers(List<Integer> numbers) {
        List<LotteryNumber> lotteryNumbers = new ArrayList<>();
        for(int number: numbers) {
            lotteryNumbers.add(LotteryNumber.from(number));
        }
        this.lotteryNumbers = lotteryNumbers;
    }

    public static LotteryNumbers auto() {
        List<Integer> numbers = LotteryNumber.randomList(NUMBERS_LENGTH);
        return new LotteryNumbers(numbers);
    }

    public static LotteryNumbers manual(List<Integer> numbers) {
        validate(numbers);
        return new LotteryNumbers(numbers);
    }

    private static void validate(List<Integer> lotteryNumbers) {
        for(int lotteryNumber: lotteryNumbers) {
            LotteryNumber.validate(lotteryNumber);
        }

        if(lotteryNumbers.size() != NUMBERS_LENGTH) {
            throw new LotteryNumbersException("로또 번호의 갯수는 " + NUMBERS_LENGTH + "개 입니다.");
        }

        if(lotteryNumbers.size() != new HashSet<>(lotteryNumbers).size()) {
            throw new LotteryNumbersException("로또 번호는 중복될 수 없습니다.");
        }
    }

    List<LotteryNumber> getLotteryNumbers() {
        return Collections.unmodifiableList(lotteryNumbers);
    }

    boolean contains(LotteryNumber number) {
        return lotteryNumbers.contains(number);
    }

    static class LotteryNumbersException extends IllegalArgumentException {
        public LotteryNumbersException(String message) {
            super(message);
        }
    }
}
