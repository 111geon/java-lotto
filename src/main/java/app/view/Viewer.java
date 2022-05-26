package app.view;

import app.model.LotteryTickets;

import java.util.List;

public class Viewer {
    public static void printLotteryTickets(LotteryTickets lotteryTickets) {
        System.out.println(lotteryTickets.size() + "개를 구매했습니다.");
        for(List<Integer> numberList: lotteryTickets.getNumberLists()) {
            System.out.println(numberList);
        }
    }
}
