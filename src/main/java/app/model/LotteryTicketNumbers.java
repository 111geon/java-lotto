package app.model;

import app.view.Receiver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LotteryTicketNumbers {
    private final List<Integer> lotteryTicketNumbers;

    public LotteryTicketNumbers() {
        this.lotteryTicketNumbers = createRandNums(
                LotteryTicketConst.MIN_NUM.getValue(),
                LotteryTicketConst.MAX_NUM.getValue(),
                LotteryTicketConst.NUM_NUMS.getValue()
        );
    }

    public LotteryTicketNumbers(List<Integer> numbers) {
        validateLotteryNumbers(numbers);
        this.lotteryTicketNumbers = numbers;
    }

    List<Integer> getNumbers() {
        return Collections.unmodifiableList(lotteryTicketNumbers);
    }

    boolean containNumber(int number) {
        return lotteryTicketNumbers.contains(number);
    }

    public static void validateLotteryNumber(int lotteryNumber) {
        if(lotteryNumber < LotteryTicketConst.MIN_NUM.getValue() ||
                lotteryNumber > LotteryTicketConst.MAX_NUM.getValue()) {
            throw new LotteryNumberException("로또 번호는 " + LotteryTicketConst.MIN_NUM.getValue() + "이상 " +
                    LotteryTicketConst.MAX_NUM.getValue() + "이하입니다.");
        }
    }

    public static void validateLotteryNumbers(List<Integer> lotteryNumbers) {
        for(int lotteryNumber: lotteryNumbers) {
            validateLotteryNumber(lotteryNumber);
        }

        if(lotteryNumbers.size() != LotteryTicketConst.NUM_NUMS.getValue()) {
            throw new LotteryNumberException("로또 번호의 갯수는 " + LotteryTicketConst.NUM_NUMS.getValue() + "개 입니다.");
        }

        if(lotteryNumbers.size() != new HashSet<>(lotteryNumbers).size()) {
            throw new LotteryNumberException("로또 번호는 중복될 수 없습니다.");
        }
    }

    private List<Integer> createRandNums(int minNum, int maxNum, int numNums) {
        List<Integer> randIntegerList = IntStream.range(minNum, maxNum+1).boxed().collect(Collectors.toList());
        Collections.shuffle(randIntegerList);
        List<Integer> sampledList = new ArrayList<>(randIntegerList.subList(0, numNums));
        Collections.sort(sampledList);
        return sampledList;
    }

    static class LotteryNumberException extends RuntimeException {
        public LotteryNumberException(String message) {
            super(message);
        }
    }
}
