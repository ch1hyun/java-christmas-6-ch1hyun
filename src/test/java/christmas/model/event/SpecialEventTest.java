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

public class SpecialEventTest {
    private static final OrderDate object = OrderDate.from(10);
    private static final OrderDate subject = OrderDate.from(14);

    static Stream<Arguments> orderDateWithExpectedBool() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject,EventConstant.FALSE),
                Arguments.arguments(OrderDate.from(17), EventConstant.TRUE),
                Arguments.arguments(OrderDate.from(25), EventConstant.TRUE),
                Arguments.arguments(OrderDate.from(30), EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedBool")
    @DisplayName("특별 이벤트는 해당 날짜의 달력에 별이 있을 경우에만 활성화 됩니다.")
    void 특별_이벤트_날짜_테스트(OrderDate orderDate, Boolean expected) {
        // given
        SpecialEvent specialEvent = SpecialEvent.from(orderDate);

        Assertions.assertThat(specialEvent.isAcitve()) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("특별 이벤트 타입을 반환해야 합니다.")
    void 할인_이벤트_반환_타입_테스트() {
        //given
        SpecialEvent specialEvent = SpecialEvent.from(object);

        Assertions.assertThat(specialEvent.getDiscountType()) // when
                .isEqualTo(DiscountType.특별); // then
    }

    static Stream<Arguments> orderDateWithExpectedString() {
        return Stream.of(
                Arguments.arguments(object, "특별 할인: -1,000원"),
                Arguments.arguments(subject, "")
        );
    }

    @ParameterizedTest
    @MethodSource("orderDateWithExpectedString")
    @DisplayName("이벤트 활성화 여부에 따라 반환 값이 달라야 합니다. 활성화인 경우 플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 출력_형식_테스트(OrderDate orderDate, String expected) {
        // given
        SpecialEvent specialEvent = SpecialEvent.from(orderDate);

        Assertions.assertThat(specialEvent.toString(orderDate.getDate())) // when
                .isEqualTo(expected); // then
    }
}
