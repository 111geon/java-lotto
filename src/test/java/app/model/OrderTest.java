package app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderTest {
    @Test
    @DisplayName("money보다 구입할 로또 금액이 큰 경우 에러")
    void validateToBuy() {
        Money money = new Money(1000);
        int toBuy = 2;
        assertThatThrownBy(() -> Order.validateToBuy(money, toBuy))
                .isInstanceOf(IllegalArgumentException.class);
    }
}