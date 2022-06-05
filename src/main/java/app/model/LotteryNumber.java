package app.model;

import java.util.*;
import java.util.stream.IntStream;

public class LotteryNumber {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 45;
    private final int number;
    private static Map<Integer, LotteryNumber> lotteryNumberMap = new HashMap<>();

    static {
        IntStream.rangeClosed(MIN_NUMBER, MAX_NUMBER)
                .forEach(i -> lotteryNumberMap.put(i, new LotteryNumber(i)));
    }

    private LotteryNumber(int number) {
        this.number = number;
    }

    public static LotteryNumber from(int number) {
        validate(number);
        LotteryNumber lotteryNumber = lotteryNumberMap.get(number);
        return lotteryNumber;
    }

    static void validate(int number) {
        if(number < MIN_NUMBER || number > MAX_NUMBER) {
            throw new LotteryNumberException("로또 번호는 " + MIN_NUMBER + "이상 " + MAX_NUMBER + "이하입니다.");
        }
    }

    static List<Integer> randomList(int length) {
        List<Integer> randomList = new ArrayList<>(lotteryNumberMap.keySet());
        Collections.shuffle(randomList);
        List<Integer> sampledList = new ArrayList<>(randomList.subList(0, length));
        Collections.sort(sampledList);
        return sampledList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LotteryNumber that = (LotteryNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    int getNumber() {
        return number;
    }

    static class LotteryNumberException extends IllegalArgumentException {
        public LotteryNumberException(String message) {
            super(message);
        }
    }
}
