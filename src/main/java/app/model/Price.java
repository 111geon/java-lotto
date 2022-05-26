package app.model;

public enum Price {
    LOTTERY_TICKET(1000);
    private final int value;

    Price(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
