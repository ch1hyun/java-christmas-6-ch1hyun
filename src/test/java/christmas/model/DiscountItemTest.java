package christmas.model;

import christmas.constants.enums.DiscountType;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountItemTest {
    static Stream<Arguments> discountTypeAndNumberWithExpectedAmount() {
        return Stream.of(
                Arguments.arguments(DiscountType.주말, 4, 8_092),
                Arguments.arguments(DiscountType.평일, 10, 20_230),
                Arguments.arguments(DiscountType.특별, 26, 1_000),
                Arguments.arguments(DiscountType.디데이, 25, 3_400)
        );
    }
    @ParameterizedTest
    @MethodSource("discountTypeAndNumberWithExpectedAmount")
    @DisplayName("할인 타입과 날짜 또는 개수가 주어졌을 때 예상되는 금액을 반환해야 합니다.")
    void 타입과_숫자가_주어졌을때_혜택값_테스트(DiscountType discountType, Integer number, Integer expectedAmount) {
        // given
        DiscountItem discountItem = DiscountItem.of(discountType, number);

        Assertions.assertThat(discountItem.getAmount()) // when
                .isEqualTo(expectedAmount); // then
    }

    static Stream<Arguments> discountTypeAndNumberWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(DiscountType.주말, 4, "주말 할인: -8,092원"),
                Arguments.arguments(DiscountType.평일, 10, "평일 할인: -20,230원"),
                Arguments.arguments(DiscountType.특별, 26, "특별 할인: -1,000원"),
                Arguments.arguments(DiscountType.디데이, 25, "크리스마스 디데이 할인: -3,400원")
        );
    }

    @ParameterizedTest
    @MethodSource("discountTypeAndNumberWithExpectedPrints")
    @DisplayName("할인 타입과 날짜 또는 개수가 주어졌을 때 플래너가 요구하는 형식으로 반환해야 합니다.")
    void 반환_메시지_형식_테스트(DiscountType discountType, Integer number, String expected) {
        // given
        DiscountItem discountItem = DiscountItem.of(discountType, number);

        Assertions.assertThat(discountItem.toString()) // when
                .isEqualTo(expected); // then
    }
}
