package app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LotteryTicketTest {
    @Test
    @DisplayName("LotteryTicket은 6자리 로또번호를 생성할 수 있다.")
    void lotteryNumbersSize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.auto();
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getNumbers().size()).isEqualTo(6);
    }

    @Test
    @DisplayName("LotteryTicket의 로또번호는 1이상 45이하여야 한다.")
    void lotteryNumberRange() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        assertThat(lotteryNumbers.getLotteryNumbers().stream().map(LotteryNumber::getNumber).collect(Collectors.toList()))
                .isEqualTo(Arrays.asList(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("로또 번호의 개수가 6개가 아니면 에러가 발생한다.")
    void lotteryNumbersTooMany() {
        assertThatThrownBy(() -> LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호의 갯수는 6개 입니다.");
    }

    @Test
    @DisplayName("로또 번호에 1 미만의 수가 포함이 되면 에러가 발생한다.")
    void lotteryNumberUnder1() {
        assertThatThrownBy(() -> LotteryNumbers.manual(Arrays.asList(0, 2, 3, 4, 5, 6)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상 45이하입니다.");
    }

    @Test
    @DisplayName("로또 번호에 45 초과의 수가 포함이 되면 에러가 발생한다.")
    void lotteryNumberOver45() {
        assertThatThrownBy(() -> LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 46)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 1이상 45이하입니다.");
    }

    @Test
    @DisplayName("LotteryTicket의 로또번호는 중복된 수가 포함될 수 없다.")
    void lotteryNumberDuplication() {
        assertThatThrownBy(() -> LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 1)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("로또 번호는 중복될 수 없습니다.");
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 1등에 당첨되었는지 알 수 있다.")
    void checkFirstPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.FIRST);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 2등에 당첨되었는지 알 수 있다.")
    void checkSecondPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 7));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.SECOND);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 3등에 당첨되었는지 알 수 있다.")
    void checkThirdPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 8));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.THIRD);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 4등에 당첨되었는지 알 수 있다.")
    void checkFourthPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 7, 8));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.FOURTH);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 5등에 당첨되었는지 알 수 있다.")
    void checkFifthPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 7, 8, 9));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.FIFTH);
    }

    @Test
    @DisplayName("LotteryTicket은 당첨번호를 받아 자신이 꽝임을 알 수 있다.")
    void checkNoPrize() {
        LotteryNumbers lotteryNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 7, 8, 9, 10));
        LotteryNumbers winningNumbers = LotteryNumbers.manual(Arrays.asList(1, 2, 3, 4, 5, 6));
        WinningNumbers winningNumbers1 = new WinningNumbers(winningNumbers, LotteryNumber.from(7));
        LotteryTicket lotteryTicket = new LotteryTicket(lotteryNumbers);
        assertThat(lotteryTicket.getWinner(winningNumbers1)).isEqualTo(Winner.NOGO);
    }
}