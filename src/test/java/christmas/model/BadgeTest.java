package christmas.model;

import christmas.model.event.DDayEvent;
import christmas.model.event.Event;
import christmas.model.event.PresentationEvent;
import christmas.model.event.SpecialEvent;
import christmas.model.event.WeekdayEvent;
import christmas.model.event.WeekendEvent;
import christmas.util.Converter;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BadgeTest {
    static Stream<Arguments> menuNameAndOrderDateWithExpectedBadge() {
        return Stream.of(
                Arguments.arguments("양송이수프-1", 1, "없음"),
                Arguments.arguments("크리스마스파스타-3", 10, "별"),
                Arguments.arguments("크리스마스파스타-4", 24, "트리"),
                Arguments.arguments("티본스테이크-10", 25, "산타")
        );
    }
    @ParameterizedTest
    @MethodSource("menuNameAndOrderDateWithExpectedBadge")
    @DisplayName("혜택 금액별로 반환되는 배지의 종류가 달라야 합니다.")
    void 혜택금액별_배지_종류_테스트(String menuItems, Integer date, String expectedBadge) {
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

        // when
        Badge badge = Badge.from(rewardAmount);

        // then
        Assertions.assertThat(badge.toString()).isEqualTo(expectedBadge);
    }
}
