package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.model.MenuItem;
import christmas.model.OrderAmount;
import christmas.model.OrderDate;
import christmas.model.OrderList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EventTest {

    private static final OrderAmount object = OrderAmount.from(130_000);
    private static final OrderAmount subject = OrderAmount.from(5_000);
    private static final OrderDate orderDate = OrderDate.from(25);
    private static final DDayEvent dDayEvent = DDayEvent.from(orderDate);
    private static final WeekdayEvent weekdayEvent = WeekdayEvent.from(orderDate);
    private static final WeekendEvent weekendEvent = WeekendEvent.from(orderDate);
    private static final SpecialEvent specialEvent = SpecialEvent.from(orderDate);

    static Stream<Arguments> orderAmountWithExpectedActive() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject, EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedActive")
    @DisplayName("할인 전 총금액이 10,000원 미만이면 이벤트가 비활성화 됩니다.")
    void 금액에_따른_이벤트_활성화_테스트(OrderAmount orderAmount, Boolean expected) {
        // given
        Event event = Event.of(
                orderAmount,
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                PresentationEvent.from(orderAmount)
        );

        Assertions.assertThat(event.isActive()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedDiscountListPrints() {
        return Stream.of(
                Arguments.arguments(
                        object,
                        "크리스마스 디데이 할인: -3,400원\n" +
                        "평일 할인: -2,023원\n" +
                        "특별 할인: -1,000원"),
                Arguments.arguments(subject, "없음")
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedDiscountListPrints")
    @DisplayName("이벤트 활성화 여부에따라 반환되는 할인 내역이 달라야 합니다.")
    void 반환_할인_내역_테스트(OrderAmount orderAmount, String expected) {
        // given
        Event event = Event.of(
                orderAmount,
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                PresentationEvent.from(orderAmount)
        );
        OrderList orderList = OrderList.from(
                List.of(MenuItem.of("아이스크림", 1))
        );

        Assertions.assertThat(event.createDiscountList(orderList, orderDate).toString()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(
                        object,
                        "크리스마스 디데이 할인: -3,400원\n" +
                        "평일 할인: -6,069원\n" +
                        "특별 할인: -1,000원\n" +
                        "증정 이벤트: -25,000원"),
                Arguments.arguments(subject, "없음")
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedPrints")
    @DisplayName("활성화 여부에 따라 반환 메시지가 달라져야 합니다. 활성화일 시 플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트(OrderAmount orderAmount, String expected) {
        // given
        Event event = Event.of(
                orderAmount,
                dDayEvent,
                weekdayEvent,
                weekendEvent,
                specialEvent,
                PresentationEvent.from(orderAmount)
        );

        Assertions.assertThat(event.toString(3, 3, orderDate.getDate())) // when
                .isEqualTo(expected); // then
    }
 }
