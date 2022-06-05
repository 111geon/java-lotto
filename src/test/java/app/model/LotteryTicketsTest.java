package app.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class LotteryTicketsTest {
    private static LotteryTickets lotteryTickets;
    private static Money money;

    @BeforeAll
    static void setLotteryTickets() {
        money = new Money(6200);
        int toBuy = 6;
        List<LotteryNumbers> lotteryNumbersList = new ArrayList<>();
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 7)));
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 8)));
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 7, 8)));
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 7, 8, 9)));
        lotteryNumbersList.add(LotteryNumbers.manual(Arrays.asList(1, 2, 7, 8, 9, 10)));
        lotteryTickets = new LotteryTickets();
        Order order = new Order(money, toBuy, lotteryNumbersList);
        lotteryTickets.appendManual(order);
    }

    @Test
    @DisplayName("수동으로 구매한 티켓 수 확인")
    void generateManualTicket() {
        assertThat(lotteryTickets.getNumManualTickets()).isEqualTo(6);
    }

    @Test
    @DisplayName("money를 입력 받으면 최대한 많은 LotteryTicket을 자동으로 생성한다.")
    void generateAutoTickets() {
        money = new Money(6000);
        lotteryTickets = new LotteryTickets();
        lotteryTickets.appendAuto(money);
        assertThat(lotteryTickets.getNumAutoTickets()).isEqualTo(6);
    }

    @Test
    @DisplayName("LotteryTickets는 당첨 번호와 보너스 번호를 받았을 때 어떤 LotteryTicket들이 당첨되었는지 알 수 있다.")
    void checkWinners() {
        WinningNumbers winningNumbers = new WinningNumbers(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6)), LotteryNumber.from(7));
        assertThat(lotteryTickets.getWinnersFrom(winningNumbers).getEntrySet())
                .contains(entry(Winner.FIFTH, 1))
                .contains(entry(Winner.FOURTH, 1))
                .contains(entry(Winner.THIRD, 1))
                .contains(entry(Winner.SECOND, 1))
                .contains(entry(Winner.FIRST, 1))
                .contains(entry(Winner.NOGO, 1));
    }

    @Test
    @DisplayName("LotteryTickets의 당첨 금액으로 수익률을 계산할 수 있다.")
    void calculateProfit() {
        WinningNumbers winningNumbers = new WinningNumbers(LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6)), LotteryNumber.from(7));
        assertThat(lotteryTickets.getWinnersFrom(winningNumbers).calculateProfitRate())
                .isEqualTo((float)2031555000/(float)6000);
    }
}