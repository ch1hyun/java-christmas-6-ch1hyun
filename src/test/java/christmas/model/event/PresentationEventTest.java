package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.MenuBoard;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PresentationEventTest {
    private static final OrderAmount object = OrderAmount.from(130_000);
    private static final OrderAmount subject = OrderAmount.from(110_000);

    static Stream<Arguments> orderAmountWithExpectedBool() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject,EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedBool")
    @DisplayName("증정 이벤트는 주문 총금액이 120,000원을 넘어 증정품이 존재하는 경우에만 활성화 됩니다.")
    void 증정_이벤트_금액_테스트(OrderAmount orderAmount, Boolean expected) {
        // given
        PresentationEvent presentationEvent = PresentationEvent.from(orderAmount);

        Assertions.assertThat(presentationEvent.isAcitve()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedMenuName() {
        return Stream.of(
                Arguments.arguments(object, "샴페인 1개"),
                Arguments.arguments(subject, "없음")
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedMenuName")
    @DisplayName("금액에 따라 다른 상태의 증정품을 반환해야 합니다.")
    void 증정_이벤트_반환_상태_테스트(OrderAmount orderAmount, String expected) {
        //given
        PresentationEvent presentationEvent = PresentationEvent.from(orderAmount);

        Assertions.assertThat(presentationEvent.getMenu()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(object, "증정 이벤트: -25,000원"),
                Arguments.arguments(subject, "")
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedPrints")
    @DisplayName("이벤트 활성화 여부에 따라 반환 값이 달라야 합니다. 활성화인 경우 플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 출력_형식_테스트(OrderAmount orderAmount, String expected) {
        // given
        PresentationEvent presentationEvent = PresentationEvent.from(orderAmount);

        Assertions.assertThat(presentationEvent.toString()) // when
                .isEqualTo(expected); // then
    }
}
