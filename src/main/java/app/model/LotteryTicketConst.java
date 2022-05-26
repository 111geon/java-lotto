package app.model;

public enum LotteryTicketConst {
    PRICE(1000), MIN_NUM(1), MAX_NUM(45), NUM_NUMS(6);
    private final int value;
    LotteryTicketConst(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
