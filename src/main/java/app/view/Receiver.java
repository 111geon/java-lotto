package app.view;

import app.model.*;

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

    public static Order askOrder(Money money) {
        int toBuy = askToBuy(money);
        List<LotteryNumbers> lotteryNumbersList = askLotteryNumbersList(toBuy);
        try {
            return new Order(money, toBuy, lotteryNumbersList);
        } catch (IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askOrder(money);
        }
    }

    public static LotteryNumbers askWinningNumbersBase() {
        System.out.println(System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요.");
        try {
            List<String> winningNumbersBaseInput = Arrays.asList(sc.nextLine().split(","));
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

    private static int askToBuy(Money money) {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
        try {
            int toBuy = parseStringToInt(sc.nextLine());
            Order.validateToBuy(money, toBuy);
            return toBuy;
        } catch (IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askToBuy(money);
        }
    }

    private static List<LotteryNumbers> askLotteryNumbersList(int toBuy) {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
        List<LotteryNumbers> lotteryNumbersList = new ArrayList<>();
        try {
            buildLotteryNumbersList(lotteryNumbersList, toBuy);
            Order.validateLotteryNumbersList(toBuy, lotteryNumbersList);
            return lotteryNumbersList;
        } catch (IllegalArgumentException error) {
            System.err.println(error.getMessage());
            return askLotteryNumbersList(toBuy);
        }
    }

    private static void buildLotteryNumbersList(List<LotteryNumbers> lotteryNumbersList, int toBuy) {
        for(int i = toBuy; i > 0; i--) {
            List<String> lotteryNumbersInput = Arrays.asList(sc.nextLine().split(","));
            lotteryNumbersList.add(LotteryNumbers.manual(parseStringListToIntList(lotteryNumbersInput)));
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
