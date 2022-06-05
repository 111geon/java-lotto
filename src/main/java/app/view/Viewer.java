package app.view;

import app.model.*;

import java.util.List;
import java.util.Map;

public class Viewer {
    public static void printLotteryTickets(LotteryTickets lotteryTickets) {
        System.out.printf("수동으로 " + lotteryTickets.getNumManualTickets() + "개, ");
        System.out.println("자동으로 " + lotteryTickets.getNumAutoTickets() + "개를 구매했습니다.");
        for(List<Integer> numbers: lotteryTickets.getNumbersList()) {
            System.out.println(numbers);
        }
    }

    public static void printStatistics(Winners winners) {
        System.out.println(System.lineSeparator() + "당첨 통계" + System.lineSeparator() + "---------");

        for(Map.Entry<Winner, Integer> winnerEntry: winners.getEntrySet()) {
            printWinnerEntry(winnerEntry);
        }

        printProfit(winners.calculateProfitRate());
    }

    private static void printWinnerEntry(Map.Entry<Winner, Integer> winnerEntry) {
        Winner winner = winnerEntry.getKey();
        if(winner == Winner.NOGO) { return; }
        System.out.printf(winner.getNumWinningNumbers() + "개 일치");
        if(winner.getNeedBonus()) {
            System.out.printf(", 보너스 볼 일치");
        }
        System.out.printf("(" + winner.getPrizeMoney() + "원) -> ");
        System.out.println(winnerEntry.getValue() + "개");
    }

    private static void printProfit(float profit) {
        System.out.printf("총 수익률은 " + String.format("%.2f", profit) + "입니다. ");
        String profitResult = "손해";
        if(profit > 1.0) {
            profitResult = "이득";
        }
        if(profit == 1.0) {
            profitResult = "본전";
        }
        System.out.println("기준이 1.0이기 때문에 결과적으로 " + profitResult + "(이)라는 의미임");
    }

}
