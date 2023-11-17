package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import christmas.constants.enums.ErrorMessage;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

public class OrderDateTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1, 36})
    @DisplayName("날짜는 1부터 31 사이의 정수이어야 합니다.")
    void 범위_1부터_31을_벗어났을때_테스트(Integer test) { // given
        Assertions.assertThatThrownBy(() -> OrderDate.from(test)) // when
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.getMessage()); // then
    }

    static Stream<Arguments> dateAndDiscountTypeWithExpectedResult() {
        return Stream.of(
                Arguments.arguments(1, DiscountType.평일, DiscountType.디데이, EventConstant.TRUE, EventConstant.TRUE),
                Arguments.arguments(3, DiscountType.평일, DiscountType.디데이, EventConstant.FALSE, EventConstant.TRUE),
                Arguments.arguments(3, DiscountType.주말, DiscountType.디데이, EventConstant.TRUE, EventConstant.TRUE),
                Arguments.arguments(3, DiscountType.특별, DiscountType.디데이, EventConstant.TRUE, EventConstant.TRUE),
                Arguments.arguments(31, DiscountType.특별, DiscountType.디데이, EventConstant.TRUE, EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("dateAndDiscountTypeWithExpectedResult")
    @DisplayName("날짜와 할인 타입이 주어졌을 때 적용가능한지 판단할 수 있어야 합니다.")
    void 할인_타입_적용가능성_테스트(Integer date, DiscountType dayDiscountType, DiscountType dateDiscountType, Boolean dayExpected, Boolean dateExpected) {
        // given
        OrderDate orderDate = OrderDate.from(date);

        Assertions.assertThat(orderDate.isDayGroup(dayDiscountType)) // when
                .isEqualTo(dayExpected); // then
        Assertions.assertThat(orderDate.isDateGroup(dateDiscountType)) // when
                .isEqualTo(dateExpected); // then
    }
}
