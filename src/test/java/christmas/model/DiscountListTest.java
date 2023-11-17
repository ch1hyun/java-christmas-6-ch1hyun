package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.enums.DiscountType;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DiscountListTest {
    @Test
    @DisplayName("리스트가 비어있는 경우 비어있는 상태인지 판단할 수 있어야 합니다.")
    void 비어있는지_테스트() {
        // given
        List<DiscountItem> discountItemList = new ArrayList<>();
        DiscountList discountList = DiscountList.from(discountItemList);

        Assertions.assertThat(discountList.isEmpty()) // when
                .isEqualTo(EventConstant.TRUE); // then
    }

    @Test
    @DisplayName("할인 아이템이 주어졌을 때 할인 금액의 총합을 구할 수 있어야 합니다.")
    void 할인_금액_총합_계산_테스트() {
        // given
        List<DiscountItem> discountItemList = new ArrayList<>();

        discountItemList.add(DiscountItem.of(DiscountType.주말, 4));
        discountItemList.add(DiscountItem.of(DiscountType.특별, 26));
        discountItemList.add(DiscountItem.of(DiscountType.디데이, 25));

        Assertions.assertThat(DiscountList.from(discountItemList).getAmount()) // when
                .isEqualTo(12_492); // then
    }

    static Stream<Arguments> discountItemsWithExpectedPrints() {
        return Stream.of(
                Arguments.arguments(List.of(), "없음"),
                Arguments.arguments(List.of(
                        DiscountItem.of(DiscountType.주말, 4),
                        DiscountItem.of(DiscountType.특별, 26),
                        DiscountItem.of(DiscountType.디데이, 25)
                ), "주말 할인: -8,092원\n특별 할인: -1,000원\n크리스마스 디데이 할인: -3,400원")
        );
    }

    @ParameterizedTest
    @MethodSource("discountItemsWithExpectedPrints")
    @DisplayName("플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트(List<DiscountItem> discountItemList, String expected) {
        // given
        DiscountList discountList = DiscountList.from(discountItemList);

        Assertions.assertThat(discountList.toString()) // when
                .isEqualTo(expected); // then
    }
}
