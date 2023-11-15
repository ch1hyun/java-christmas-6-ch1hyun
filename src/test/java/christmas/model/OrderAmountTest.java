package christmas.model;

import christmas.constants.EventConstant;
import christmas.model.event.DDayEvent;
import christmas.model.event.Event;
import christmas.model.event.PresentationEvent;
import christmas.model.event.SpecialEvent;
import christmas.model.event.WeekdayEvent;
import christmas.model.event.WeekendEvent;
import christmas.util.Converter;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderAmountTest {
    static Stream<Arguments> subTotalAndAmountWithExpectedBool() {
        return Stream.of(
                Arguments.arguments(10_000, 5_000, EventConstant.TRUE),
                Arguments.arguments(10_000, 15_000, EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("subTotalAndAmountWithExpectedBool")
    @DisplayName("할인 전 총금액과 비교 금액이 주어졌을 때 더 크거나 같은지 판단할 수 있어야 합니다.")
    void 금액_비교_테스트(Integer subTotal, Integer amount, Boolean expected) {
        // given
        OrderAmount orderAmount = OrderAmount.from(subTotal);

        Assertions.assertThat(orderAmount.isGreaterThanOrEqualTo(amount)) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> menuNameAndOrderDateWithExpectedTotalAmount() {
        return Stream.of(
                Arguments.arguments("양송이수프-1", 1, "6,000원"),
                Arguments.arguments("크리스마스파스타-3", 10, "66,031원"),
                Arguments.arguments("크리스마스파스타-4", 24, "87,608원"),
                Arguments.arguments("티본스테이크-10", 25, "546,600원")
        );
    }

    @ParameterizedTest
    @MethodSource("menuNameAndOrderDateWithExpectedTotalAmount")
    @DisplayName("메뉴 아이템과 날짜가 주어졌을 때 할인 후 총금액을 계산할 수 있어야 합니다.")
    void 할인_후_총금액_반환_테스트(String menuItems, Integer date, String expected) {
        // given
        OrderDate orderDate = OrderDate.from(date);
        OrderList orderList = OrderList.from(
                Converter.parseStringToMenuList(menuItems)
        );
        OrderAmount orderAmount = OrderAmount.from(orderList.getAmount());
        PresentationEvent presentationEvent = PresentationEvent.from(orderAmount);
        Event event = Event.of(
                orderAmount,
                DDayEvent.from(orderDate),
                WeekdayEvent.from(orderDate),
                WeekendEvent.from(orderDate),
                SpecialEvent.from(orderDate),
                presentationEvent
        );
        RewardAmount rewardAmount = RewardAmount.of(event.createDiscountList(orderList, orderDate), presentationEvent.getPresentationItem());

        Assertions.assertThat(orderAmount.getTotalAmount(rewardAmount)) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("할인 전 총주문 금액이 주어졌을 때 플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 출력_형식_테스트() {
        // given
        OrderAmount orderAmount = OrderAmount.from(10_000);

        Assertions.assertThat(orderAmount.toString()) // when
                .isEqualTo("10,000원"); // then
    }
}
