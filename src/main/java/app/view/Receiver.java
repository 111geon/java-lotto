package app.view;

import app.model.LotteryTicketConst;
import app.model.LotteryTicketNumbers;

import java.util.*;

enum NumTicket {
    MIN(1), MAX(1000);
    private final int value;
    NumTicket(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}

enum Input {
    MONEY_INPUT("구입금액"), WINNING_NUMBER("당첨번호"), BONUS_NUMBER("보너스번호");
    private final String value;
    Input(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

public class Receiver {
    private static Scanner sc = new Scanner(System.in);

    public static int askMoney() {
        System.out.println("구입금액을 입력해 주세요.");
        int money;

        try {
            money = parseStringToInt(sc.nextLine(), Input.MONEY_INPUT);
            validateMoney(money);
            return money;
        } catch(InputException error) {
            System.err.println(error.getMessage());
            return askMoney();
        }
    }

    public static List<Integer> askWinningNumbers() {
        System.out.println(System.lineSeparator() + "지난 주 당첨 번호를 입력해 주세요.");
        List<String> winningNumbersInput = Arrays.asList(sc.nextLine().split(","));
        try {
            List<Integer> winningNumbers = parseStringListToIntList(winningNumbersInput);
            LotteryTicketNumbers.validateLotteryNumbers(winningNumbers);
            return winningNumbers;
        } catch(InputException error) {
            System.err.println(error.getMessage());
            return askWinningNumbers();
        }
    }

    public static int askBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 볼을 입력해 주세요.");
        int bonusNumber;

        try {
            bonusNumber = parseStringToInt(sc.nextLine(), Input.BONUS_NUMBER);
            validateBonusNumbers(bonusNumber, winningNumbers);
            return bonusNumber;
        } catch(InputException error) {
            System.err.println(error.getMessage());
            return askBonusNumber(winningNumbers);
        }
    }

    private static int parseStringToInt(String string, Input input) {
        try {
            return Integer.parseInt(string.strip());
        } catch(java.lang.NumberFormatException error) {
            throw new InputException(input.getValue() + "은(는) Integer이어야 합니다.");
        }
    }

    private static List<Integer> parseStringListToIntList(List<String> stringList) {
        List<Integer> intList = new ArrayList<>();
        for(String string: stringList) {
            intList.add(parseStringToInt(string, Input.WINNING_NUMBER));
        }
        return intList;
    }

    private static void validateMoney(int money) {
        int minMoney = LotteryTicketConst.PRICE.getValue() * NumTicket.MIN.getValue();
        int maxMoney = LotteryTicketConst.PRICE.getValue() * NumTicket.MAX.getValue();

        if(money < minMoney) {
            throw new InputException("최소 구입 금액은 " + minMoney + "원 입니다.");
        }

        if(money > maxMoney) {
            throw new InputException("최대 구입 금액은 " + maxMoney + "원 입니다.");
        }
    }

    private static void validateBonusNumbers(int bonusNumber, List<Integer> winningNumbers) {
        LotteryTicketNumbers.validateLotteryNumber(bonusNumber);

        if(winningNumbers.contains(bonusNumber)) {
            throw new InputException("보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    private static class InputException extends RuntimeException {
        public InputException(String message) {
            super(message);
        }
    }
}
