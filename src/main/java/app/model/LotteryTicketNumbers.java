package app.model;

import java.util.ArrayList;
import java.util.Collections;
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

    List<Integer> getNumbers() {
        return Collections.unmodifiableList(lotteryTicketNumbers);
    }

    private List<Integer> createRandNums(int minNum, int maxNum, int numNums) {
        List<Integer> randIntegerList = IntStream.range(minNum, maxNum+1).boxed().collect(Collectors.toList());
        Collections.shuffle(randIntegerList);
        List<Integer> sampledList = new ArrayList<>(randIntegerList.subList(0, numNums));
        Collections.sort(sampledList);
        return sampledList;
    }
}
