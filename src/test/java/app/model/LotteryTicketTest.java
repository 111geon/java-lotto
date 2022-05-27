package app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteryTicketTest {
    LotteryTicketNumbers lotteryTicketNumbers = new LotteryTicketNumbers();
    LotteryTicket lotteryTicket = new LotteryTicket(lotteryTicketNumbers);

    @Test
    @DisplayName("LotteryTicket은 6자리 로또번호를 생성할 수 있다.")
    void lotteryNumbersSize() {
        assertThat(lotteryTicket.getNumberList().size()).isEqualTo(LotteryTicketConst.NUM_NUMS.getValue());
    }

    @Test
    @DisplayName("LotteryTicket의 로또번호는 1이상 45이하여야 한다.")
    void lotteryNumberRange() {
        LotteryTicketNumbers lotteryTicketNumbers1 = new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotteryTicketNumbers1.getNumbers()).isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 에러가 발생한다.")
    void lotteryNumbersTooMany() {
        assertThatThrownBy(() -> new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(LotteryTicketNumbers.LotteryNumberException.class)
                .hasMessage("로또 번호의 갯수는 6개 입니다.");
    }

    @Test
    @DisplayName("로또 번호에 1 미만의 수가 포함이 되면 에러가 발생한다.")
    void lotteryNumberUnder1() {
        assertThatThrownBy(() -> new LotteryTicketNumbers(Arrays.asList(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(LotteryTicketNumbers.LotteryNumberException.class)
                .hasMessage("로또 번호는 1이상 45이하입니다.");
    }

    @Test
    @DisplayName("로또 번호에 45 초과의 수가 포함이 되면 에러가 발생한다.")
    void lotteryNumberOver45() {
        assertThatThrownBy(() -> new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(LotteryTicketNumbers.LotteryNumberException.class)
                .hasMessage("로또 번호는 1이상 45이하입니다.");
    }

    @Test
    @DisplayName("LotteryTicket의 로또번호는 중복된 수가 포함될 수 없다.")
    void lotteryNumberDuplication() {
        assertThatThrownBy(() -> new LotteryTicketNumbers(Arrays.asList(1, 2, 3, 4, 5, 1)))
                .isInstanceOf(LotteryTicketNumbers.LotteryNumberException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 1등에 당첨되었는지 알 수 있다.")
    void checkFirstPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        assertThat(lotteryTicket.getWinner(winningNumbers, 0)).isEqualTo(Winner.FIRST);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 2등에 당첨되었는지 알 수 있다.")
    void checkSecondPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        int bonusNumber = winningNumbers.get(0);
        winningNumbers.set(0, 0);
        assertThat(lotteryTicket.getWinner(winningNumbers, bonusNumber)).isEqualTo(Winner.SECOND);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 3등에 당첨되었는지 알 수 있다.")
    void checkThirdPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        winningNumbers.set(0, 0);
        assertThat(lotteryTicket.getWinner(winningNumbers, 0)).isEqualTo(Winner.THIRD);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 4등에 당첨되었는지 알 수 있다.")
    void checkFourthPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        winningNumbers.set(0, 0);
        winningNumbers.set(1, 0);
        assertThat(lotteryTicket.getWinner(winningNumbers, 0)).isEqualTo(Winner.FOURTH);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 5등에 당첨되었는지 알 수 있다.")
    void checkFifthPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        winningNumbers.set(0, 0);
        winningNumbers.set(1, 0);
        winningNumbers.set(2, 0);
        assertThat(lotteryTicket.getWinner(winningNumbers, 0)).isEqualTo(Winner.FIFTH);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 꽝임을 알 수 있다.")
    void checkNoPrize() {
        List<Integer> winningNumbers = new ArrayList<>(lotteryTicket.getNumberList());
        winningNumbers.set(0, 0);
        winningNumbers.set(1, 0);
        winningNumbers.set(2, 0);
        winningNumbers.set(3, 0);
        assertThat(lotteryTicket.getWinner(winningNumbers, 0)).isEqualTo(Winner.NOGO);
    }
}