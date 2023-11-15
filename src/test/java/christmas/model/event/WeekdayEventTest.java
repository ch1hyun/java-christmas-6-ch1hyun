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

public class WeekdayEventTest {
    private static final OrderDate object = OrderDate.from(20);
    private static final OrderDate subject = OrderDate.from(23);

    static Stream<Arguments> orderDateWithExpectedActive() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject,EventConstant.FALSE),
                Arguments.arguments(OrderDate.from(25), EventConstant.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedActive")
    @DisplayName("평일 이벤트는 해당 날짜가 평일일 경우에만 활성화 됩니다.")
    void 평일_이벤트_날짜_테스트(OrderDate orderDate, Boolean expected) {
        // given
        WeekdayEvent weekdayEvent = WeekdayEvent.from(orderDate);

        Assertions.assertThat(weekdayEvent.isAcitve()) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("평일 이벤트 타입을 반환해야 합니다.")
    void 할인_이벤트_반환_타입_테스트() {
        //given
        WeekdayEvent weekdayEvent = WeekdayEvent.from(object);

        Assertions.assertThat(weekdayEvent.getDiscountType()) // when
                .isEqualTo(DiscountType.평일); // then
    }

    @Test
    @DisplayName("평일 이벤트는 디저트 메뉴판을 반환해야 합니다.")
    void 메뉴판_반환_타입_테스트() {
        //given
        WeekdayEvent weekdayEvent = WeekdayEvent.from(object);

        Assertions.assertThat(weekdayEvent.getMenuBoard()) // when
                .isEqualTo(MenuBoard.디저트); // then
    }

    static Stream<Arguments> orderDateAndCountWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(object, 4, "평일 할인: -8,092원"),
                Arguments.arguments(subject, 11, "")
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateAndCountWithExpectedPrints")
    @DisplayName("이벤트 활성화 여부에 따라 반환 값이 달라야 합니다. 활성화인 경우 플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트(OrderDate orderDate, Integer count, String expected) {
        // given
        WeekdayEvent weekdayEvent = WeekdayEvent.from(orderDate);

        Assertions.assertThat(weekdayEvent.toString(count)) // when
                .isEqualTo(expected); // then
    }
}
