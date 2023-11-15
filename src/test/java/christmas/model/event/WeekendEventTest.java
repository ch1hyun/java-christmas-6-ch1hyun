package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.MenuBoard;
import christmas.model.OrderDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class WeekendEventTest {
    private static final OrderDate object = OrderDate.from(9);
    private static final OrderDate subject = OrderDate.from(25);

    static Stream<Arguments> orderDateWithExpectedBool() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject,EventConstant.FALSE),
                Arguments.arguments(OrderDate.from(31), EventConstant.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedBool")
    @DisplayName("주말 이벤트는 해당 날짜가 주말일 경우에만 활성화 됩니다.")
    void 주말_이벤트_날짜_테스트(OrderDate orderDate, Boolean expected) {
        // given
        WeekendEvent weekendEvent = WeekendEvent.from(orderDate);

        Assertions.assertThat(weekendEvent.isAcitve()) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("주말 이벤트 타입을 반환해야 합니다.")
    void 할인_이벤트_반환_타입_테스트() {
        //given
        WeekendEvent weekendEvent = WeekendEvent.from(object);

        Assertions.assertThat(weekendEvent.getDiscountType()) // when
                .isEqualTo(DiscountType.주말); // then
    }

    @Test
    @DisplayName("주말 이벤트는 메인 메뉴판을 반환해야 합니다.")
    void 메뉴판_반환_타입_테스트() {
        //given
        WeekendEvent weekendEvent = WeekendEvent.from(object);

        Assertions.assertThat(weekendEvent.getMenuBoard()) // when
                .isEqualTo(MenuBoard.메인); // then
    }

    static Stream<Arguments> orderDateAndCountWithExpectedString() {
        return Stream.of(
                Arguments.arguments(object, 3, "주말 할인: -6,069원"),
                Arguments.arguments(subject, 2, "")
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateAndCountWithExpectedString")
    @DisplayName("이벤트 활성화 여부에 따라 반환 값이 달라야 합니다. 활성화인 경우 플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 출력_형식_테스트(OrderDate orderDate, Integer count, String expected) {
        // given
        WeekendEvent weekendEvent = WeekendEvent.from(orderDate);

        Assertions.assertThat(weekendEvent.toString(count)) // when
                .isEqualTo(expected); // then
    }
}
