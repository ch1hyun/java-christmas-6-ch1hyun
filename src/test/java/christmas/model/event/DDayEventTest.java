package christmas.model.event;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.model.OrderDate;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DDayEventTest {
    private static final OrderDate object = OrderDate.from(14);
    private static final OrderDate subject = OrderDate.from(28);

    static Stream<Arguments> orderDateWithExpectedActive() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject,EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedActive")
    @DisplayName("크리스마스 디데이 이벤트는 1 ~ 25 사이의 날짜에만 활성화 됩니다.")
    void 크리스마스_이벤트_날짜_테스트(OrderDate orderDate, Boolean expected) {
        // given
        DDayEvent dDayEvent = DDayEvent.from(orderDate);

        Assertions.assertThat(dDayEvent.isAcitve()) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("크리스마스 이벤트 타입을 반환해야 합니다.")
    void 할인_이벤트_반환_타입_테스트() {
        //given
        DDayEvent dDayEvent = DDayEvent.from(object);

        Assertions.assertThat(dDayEvent.getDiscountType()) // when
                .isEqualTo(DiscountType.디데이); // then
    }

    static Stream<Arguments> orderDateWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(object, "크리스마스 디데이 할인: -2,300원"),
                Arguments.arguments(subject, "")
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedPrints")
    @DisplayName("이벤트 활성화 여부에 따라 반환 값이 달라야 합니다. 활성화인 경우 플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트(OrderDate orderDate, String expected) {
        // given
        DDayEvent dDayEvent = DDayEvent.from(orderDate);

        Assertions.assertThat(dDayEvent.toString(orderDate.getDate())) // when
                .isEqualTo(expected); // then
    }
}
