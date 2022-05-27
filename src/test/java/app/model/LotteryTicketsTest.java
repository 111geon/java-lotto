package app.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class LotteryTicketsTest {
    static List<LotteryTicket> lotteryTicketList = new ArrayList<>();
    static LotteryTickets lotteryTickets = new LotteryTickets();

    @Test
    @DisplayName("money를 입력 받으면 최대한 많은 LotteryTicket을 생성한다.")
    void generateTickets() {
        LotteryTickets lotteryTickets = new LotteryTickets();
        int moneyInput = 3200;
        lotteryTickets.setLotteryTickets(moneyInput);
        assertThat(lotteryTickets.size()).isEqualTo(moneyInput/LotteryTicketConst.PRICE.getValue());
    }

    @BeforeAll
    static void setLotteryTickets() {
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 6))));
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 7))));
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 8))));
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 7, 8))));
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 7, 8, 9))));
        lotteryTicketList.add(new LotteryTicket(new LotteryTicketNumbers(Arrays.asList(1, 2, 7, 8, 9, 10))));
        lotteryTickets.setLotteryTickets(lotteryTicketList);
    }

    @Test
    @DisplayName("LotteryTickets는 당첨 번호와 보너스 번호를 받았을 때 어떤 LotteryTicket들이 당첨되었는지 알 수 있다.")
    void checkWinners() {
        Map<Winner, Integer> winners = new LinkedHashMap<>();
        winners.put(Winner.FIFTH, 1);
        winners.put(Winner.FOURTH, 1);
        winners.put(Winner.THIRD, 1);
        winners.put(Winner.SECOND, 1);
        winners.put(Winner.FIRST, 1);
        winners.put(Winner.NOGO, 1);
        assertThat(lotteryTickets.getWinners(Arrays.asList(1, 2, 3, 4, 5, 6), 7).getWinners())
                .isEqualTo(winners);
    }

    @Test
    @DisplayName("LotteryTickets의 당첨 금액으로 수익률을 계산할 수 있다.")
    void calculateProfit() {
        assertThat(lotteryTickets.getWinners(Arrays.asList(1, 2, 3, 4, 5, 6), 7).calculateProfit())
                .isEqualTo((float)2031555000/(float)6000);
    }
}