package app.view;

import app.model.LotteryNumber;
import app.model.LotteryNumbers;
import app.model.Money;
import app.model.WinningNumbers;

import java.util.*;

public class Receiver {
    private static Scanner sc = new Scanner(System.in);

    public static Money askMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        try {
            return new Money(parseStringToInt(sc.nextLine()));
        } catch(IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askMoney();
        }
    }

    public static LotteryNumbers askWinningNumbersBase() {
        System.out.println(System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요.");
        List<String> winningNumbersBaseInput = Arrays.asList(sc.nextLine().split(","));
        try {
            return LotteryNumbers.manual(parseStringListToIntList(winningNumbersBaseInput));
        } catch(IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askWinningNumbersBase();
        }
    }

    public static LotteryNumber askBonusNumber(LotteryNumbers winningNumbersBase) {
        System.out.println("보너스 볼을 입력해 주세요.");
        try {
            LotteryNumber bonusNumber = LotteryNumber.from(parseStringToInt(sc.nextLine()));
            new WinningNumbers(winningNumbersBase, bonusNumber);
            return bonusNumber;
        } catch(IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askBonusNumber(winningNumbersBase);
        }
    }

    private static int parseStringToInt(String string) {
        try {
            return Integer.parseInt(string.strip());
        } catch(java.lang.NumberFormatException error) {
            throw new InputException("입력값은 Integer이어야 합니다.");
        }
    }

    private static List<Integer> parseStringListToIntList(List<String> stringList) {
        List<Integer> intList = new ArrayList<>();
        for(String string: stringList) {
            intList.add(parseStringToInt(string));
        }
        return intList;
    }

    private static class InputException extends IllegalArgumentException {
        public InputException(String message) {
            super(message);
        }
    }
}
