package app.view;

import app.model.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Viewer {
    public static void printLotteryTickets(int numManual, int numAuto, List<List<Integer>> numbersList) {
        System.out.printf("수동으로 " + numManual + "개, ");
        System.out.println("자동으로 " + numAuto + "개를 구매했습니다.");
        for(List<Integer> numbers: numbersList) {
            System.out.println(numbers);
        }
    }

    public static void printStatistics(Set<Map.Entry<Winner, Integer>> entrySet, float profitRate) {
        System.out.println(System.lineSeparator() + "당첨 통계" + System.lineSeparator() + "---------");

        for(Map.Entry<Winner, Integer> winnerEntry: entrySet) {
            printWinnerEntry(winnerEntry);
        }

        System.out.printf("총 수익률은 " + String.format("%.2f", profitRate) + "입니다. ");
        System.out.println("기준이 1.0이기 때문에 결과적으로 " + balanceMessage(profitRate) + "(이)라는 의미임");
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

    private static String balanceMessage(float profit) {
        if(profit > 1.0) { return "이득"; }
        if(profit < 1.0) { return "손해"; }
        return "본전";
    }

}
