package app.view;

import app.model.LotteryTicketConst;

import java.util.Scanner;

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

public class Receiver {
    private static Scanner sc = new Scanner(System.in);

    public static int askMoney() {
        System.out.println("구입금액을 입력해 주세요.");

        String moneyInput = sc.nextLine();
        int money;

        try {
            money = parseMoney(moneyInput);
            validateMoney(money);
            return money;
        } catch(InputMoneyException error) {
            System.err.println(error.getMessage());
            return askMoney();
        }
    }

    private static int parseMoney(String moneyInput) {
        try {
            return Integer.parseInt(moneyInput);
        } catch(java.lang.NumberFormatException error) {
            throw new InputMoneyException("구입 금액은 Integer이어야 합니다.");
        }
    }

    private static void validateMoney(int money) {
        int minMoney = LotteryTicketConst.PRICE.getValue() * NumTicket.MIN.getValue();
        int maxMoney = LotteryTicketConst.PRICE.getValue() * NumTicket.MAX.getValue();

        if(money < minMoney) {
            throw new InputMoneyException("최소 구입 금액은 " + minMoney + "원 입니다.");
        }

        if(money > maxMoney) {
            throw new InputMoneyException("최대 구입 금액은 " + maxMoney + "원 입니다.");
        }
    }

    private static class InputMoneyException extends RuntimeException {
        public InputMoneyException(String message) {
            super(message);
        }
    }
}
