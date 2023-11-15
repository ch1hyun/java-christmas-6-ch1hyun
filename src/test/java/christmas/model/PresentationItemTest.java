package christmas.model;

import christmas.constants.EventConstant;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class PresentationItemTest {
    private static final OrderAmount object = OrderAmount.from(50_000);
    private static final OrderAmount subject = OrderAmount.from(130_000);

    static Stream<Arguments> orderAmountWithExpectedAmount() {
        return Stream.of(
                Arguments.arguments(object, 0),
                Arguments.arguments(subject, 25_000)
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedAmount")
    @DisplayName("증정품의 가격을 계산할 수 있어야 합니다.")
    void 증정품_가격_테스트(OrderAmount orderAmount, Integer expected) { // given
        Assertions.assertThat(PresentationItem.from(orderAmount).getAmount()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedBool() {
        return Stream.of(
                Arguments.arguments(object, EventConstant.TRUE),
                Arguments.arguments(subject, EventConstant.FALSE)
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedBool")
    @DisplayName("증정품이 없는지 판단할 수 있어야 합니다.")
    void 증정품이_비어있는지_테스트(OrderAmount orderAmount, Boolean expected) { // given
        Assertions.assertThat(PresentationItem.from(orderAmount).isEmpty()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> orderAmountWithExpectedString() {
        return Stream.of(
                Arguments.arguments(object, "없음"),
                Arguments.arguments(subject, "샴페인 1개")
        );
    }

    @ParameterizedTest
    @MethodSource("orderAmountWithExpectedString")
    @DisplayName("플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 출력_형식_테스트(OrderAmount orderAmount, String expected) { // given
        Assertions.assertThat(PresentationItem.from(orderAmount).toString()) // when
                .isEqualTo(expected); // then
    }
}
