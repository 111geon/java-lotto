package app.model;

import java.util.List;

public class Order {
    private Money money;
    private int toBuy;
    private List<LotteryNumbers> lotteryNumbersList;

    public Order(Money money, int toBuy, List<LotteryNumbers> lotteryNumbersList) {
        validateToBuy(money, toBuy);
        validateLotteryNumbersList(toBuy, lotteryNumbersList);
        this.money = money;
        this.toBuy = toBuy;
        this.lotteryNumbersList = lotteryNumbersList;
    }

    public static void validateToBuy(Money money, int toBuy) {
        if(toBuy < 0) {
            throw new OrderException("구입할 로또 수는 음수가 될 수 없습니다.");
        }
        if(money.getMoney() < toBuy * Money.getTicketPrice()) {
            throw new OrderException("구입할 로또 금액이 입력된 구입 금액을 초과합니다.");
        }
    }

    public static void validateLotteryNumbersList(int toBuy, List<LotteryNumbers> lotteryNumbersList) {
        if(toBuy != lotteryNumbersList.size()) {
            throw new OrderException("구입할 로또 수와 입력된 로또 번호들의 수가 맞지 않습니다.");
        }
    }

    int getToBuy() {
        return toBuy;
    }

    void buyTicket() {
        money.buyTicket();
        toBuy -= 1;
    }

    LotteryNumbers popLotteryNumbers() {
        if(lotteryNumbersList.size() == 0) { return null; }
        LotteryNumbers lotteryNumbers = lotteryNumbersList.get(lotteryNumbersList.size()-1);
        lotteryNumbersList.remove(lotteryNumbersList.size()-1);
        return lotteryNumbers;
    }

    static class OrderException extends IllegalArgumentException {
        public OrderException(String message) {
            super(message);
        }
    }
}
