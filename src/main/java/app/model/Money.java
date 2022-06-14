package app.model;

import java.util.Objects;

public class Money {
    private static final int TICKET_PRICE = 1000;
    private static final int MIN_NUM_TICKET = 1;
    private static final int MAX_NUM_TICKET = 1000;
    private int money;

    public Money(int money) {
        validate(money);
        this.money = money;
    }

    static int getTicketPrice() {
        return TICKET_PRICE;
    }

    int getMoney() {
        return money;
    }

    void buyTicket() {
        money -= TICKET_PRICE;
    }

    private void validate(int money) {
        int minMoney = MIN_NUM_TICKET * TICKET_PRICE;
        int maxMoney = MAX_NUM_TICKET * TICKET_PRICE;

        if(money < minMoney) {
            throw new MoneyException("최소 구입 금액은 " + minMoney + "원 입니다.");
        }

        if(money > maxMoney) {
            throw new MoneyException("최대 구입 금액은 " + maxMoney + "원 입니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money that = (Money) o;
        return money == that.money;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

    static class MoneyException extends IllegalArgumentException {
        public MoneyException(String message) {
            super(message);
        }
    }
}
