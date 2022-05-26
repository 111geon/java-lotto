package app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class LotteryTicketsTest {

    @Test
    @DisplayName("money를 입력 받으면 최대한 많은 LotteryTicket을 생성한다.")
    void generateTickets() {
        LotteryTickets lotteryTickets = new LotteryTickets();
        int moneyInput = 3200;
        lotteryTickets.generateTickets(moneyInput);
        assertThat(lotteryTickets.size()).isEqualTo(moneyInput/LotteryTicketConst.PRICE.getValue());
    }

    @Test
    @DisplayName("LotteryTicket은 6자리 로또번호를 생성할 수 있다.")
    void generateTicket() {
        LotteryTickets lotteryTickets = new LotteryTickets();
        int moneyInput = 1000;
        lotteryTickets.generateTickets(moneyInput);
        assertThat(lotteryTickets.getNumberLists().get(0).size()).isEqualTo(LotteryTicketConst.NUM_NUMS.getValue());
    }
}