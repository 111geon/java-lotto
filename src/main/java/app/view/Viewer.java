package app.view;

import app.model.LotteryTickets;
import app.model.Winner;
import app.model.Winners;

import java.util.List;
import java.util.Map;

public class Viewer {
    public static void printLotteryTickets(LotteryTickets lotteryTickets) {
        System.out.println(lotteryTickets.size() + "개를 구매했습니다.");
        for(List<Integer> numberList: lotteryTickets.getNumberLists()) {
            System.out.println(numberList);
        }
    }

    public static void printStatistics(Winners winners, float profit) {
        System.out.println(System.lineSeparator() + "당첨 통계" + System.lineSeparator() + "---------");

        for(Map.Entry<Winner, Integer> winnerEntry: winners.getWinners().entrySet()) {
            printWinnerEntry(winnerEntry);
        }

        printProfit(profit);
    }

    private static void printWinnerEntry(Map.Entry<Winner, Integer> winnerEntry) {
        Winner winner = winnerEntry.getKey();
        if(winner == Winner.NOGO) { return; }
        System.out.printf(winner.getNumWinningNumbers() + "개 일치");
        if(winner == Winner.SECOND) {
            System.out.printf(", 보너스 볼 일치");
        }
        System.out.printf("(" + winner.getPrizeMoney() + "원) -> ");
        System.out.println(winnerEntry.getValue() + "개");
    }

    private static void printProfit(float profit) {
        System.out.printf("총 수익률은 " + String.format("%.2f", profit) + "입니다. ");
        String profitResult = "손해";
        if(profit > 1) {
            profitResult = "이득";
        }
        if(profit == 1) {
            profitResult = "본전";
        }
        System.out.println("기준이 1이기 때문에 결과적으로 " + profitResult + "(이)라는 의미임");
    }

}
