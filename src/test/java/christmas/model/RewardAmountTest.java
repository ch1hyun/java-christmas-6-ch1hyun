package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class RewardAmountTest {
    private final DiscountList discountList = DiscountList.from(
            List.of(
                DiscountItem.of(DiscountType.주말, 4),
                DiscountItem.of(DiscountType.특별, 4),
                DiscountItem.of(DiscountType.디데이, 4)
            )
    );

    private final PresentationItem presentationItem = PresentationItem.from(
            OrderAmount.from(130_000)
    );

    @Test
    @DisplayName("할인 금액을 반환할 수 있어야 합니다.")
    void 할인_금액_반환_테스트() {
        //given
        RewardAmount rewardAmount = RewardAmount.of(discountList, presentationItem);

        Assertions.assertThat(rewardAmount.getDiscountAmount()) // when
                .isEqualTo(10_392); // then
    }

    static Stream<Arguments> amountsWithExpectedResult() {
        return Stream.of(
                Arguments.arguments(20_000, EventConstant.TRUE),
                Arguments.arguments(50_000, EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("amountsWithExpectedResult")
    @DisplayName("주어진 금액보다 크거나 같은지 판단할 수 있어야 합니다.")
    void 크거나_같은지_판단_테스트(Integer amount, Boolean expected) {
        // given
        RewardAmount rewardAmount = RewardAmount.of(discountList, presentationItem);

        Assertions.assertThat(rewardAmount.isGreaterThanOrEqualTo(amount)) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트() {
        // given
        RewardAmount rewardAmount = RewardAmount.of(discountList, presentationItem);

        Assertions.assertThat(rewardAmount.toString()) // when
                .isEqualTo("-35,392원"); // then
    }
}
