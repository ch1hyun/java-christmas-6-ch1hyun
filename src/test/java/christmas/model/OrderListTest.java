package christmas.model;

import christmas.constants.enums.ErrorMessage;
import christmas.constants.enums.MenuBoard;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class OrderListTest {
    static Stream<Arguments> duplicatedMenuList() {
        return Stream.of(
                Arguments.arguments(List.of(
                        MenuItem.of("양송이수프", 2),
                        MenuItem.of("양송이수프", 2)
                )),
                Arguments.arguments(List.of(
                        MenuItem.of("양송이수프", 2),
                        MenuItem.of("티본스테이크", 3),
                        MenuItem.of("양송이수프", 1)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("duplicatedMenuList")
    @DisplayName("주문 메뉴 리스트에 중복이 존재하면 안됩니다.")
    void 중복_테스트(List<MenuItem> menuItemList) { // given
        Assertions.assertThatThrownBy(() -> OrderList.from(menuItemList)) // when
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage()); // then
    }

    static Stream<Arguments> menuListSingleBeverageItem() {
        return Stream.of(
                Arguments.arguments(List.of(
                        MenuItem.of("제로콜라", 1)
                )),
                Arguments.arguments(List.of(
                        MenuItem.of("샴페인", 2),
                        MenuItem.of("레드와인", 1)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("menuListSingleBeverageItem")
    @DisplayName("음료수만 주문하면 안됩니다.")
    void 음료수_주문_불가_테스트(List<MenuItem> menuItemList) { // given
        Assertions.assertThatThrownBy(() -> OrderList.from(menuItemList)) // when
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage()); // then
    }

    static Stream<Arguments> menuListCountOver20() {
        return Stream.of(
                Arguments.arguments(List.of(
                )),
                Arguments.arguments(List.of(
                        MenuItem.of("티본스테이크", 21)
                )),
                Arguments.arguments(List.of(
                        MenuItem.of("양송이수프", 11),
                        MenuItem.of("레드와인", 10)
                ))
        );
    }

    @ParameterizedTest
    @MethodSource("menuListCountOver20")
    @DisplayName("메뉴의 개수는 1개 이상이며 20개를 넘어가면 안됩니다.")
    void 메뉴_개수_테스트(List<MenuItem> menuItemList) { // given
        Assertions.assertThatThrownBy(() -> OrderList.from(menuItemList)) // when
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage()); // then
    }

    static Stream<Arguments> menuListWithExpectedAmount() {
        return Stream.of(
                Arguments.arguments(List.of(
                        MenuItem.of("티본스테이크", 10)
                ), 550_000),
                Arguments.arguments(List.of(
                        MenuItem.of("양송이수프", 1),
                        MenuItem.of("레드와인", 2),
                        MenuItem.of("아이스크림", 4)
                ), 146_000)
        );
    }

    @ParameterizedTest
    @MethodSource("menuListWithExpectedAmount")
    @DisplayName("총금액을 계산할 수 있어야 합니다.")
    void 주문_메뉴_계산_테스트(List<MenuItem> menuItemList, Integer expected) {
        // given
        OrderList orderList = OrderList.from(menuItemList);

        Assertions.assertThat(orderList.getAmount()) // when
                .isEqualTo(expected); // then
    }

    static Stream<Arguments> menuListAndMenuGroupWithExpectedMatchCount() {
        return Stream.of(
                Arguments.arguments(List.of(
                        MenuItem.of("티본스테이크", 10)
                ), MenuBoard.메인, 10),
                Arguments.arguments(List.of(
                        MenuItem.of("양송이수프", 1),
                        MenuItem.of("레드와인", 2),
                        MenuItem.of("아이스크림", 4),
                        MenuItem.of("샴페인", 1)
                ), MenuBoard.음료, 3)
        );
    }

    @ParameterizedTest
    @MethodSource("menuListAndMenuGroupWithExpectedMatchCount")
    @DisplayName("특정 그룹에 속하는 메뉴가 몇 개 있는지 셀 수 있어야 합니다.")
    void 그룹에_속하는_메뉴_개수_테스트(List<MenuItem> menuItemList, MenuBoard menuBoard, Integer expected) {
        // given
        OrderList orderList = OrderList.from(menuItemList);

        Assertions.assertThat(orderList.getCountMatchMenuGroup(menuBoard)) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("플래너가 요구하는 형식으로 반환되어야 합니다.")
    void 반환_메시지_형식_테스트() {
        // given
        OrderList orderList = OrderList.from(
                List.of(
                        MenuItem.of("티본스테이크", 1),
                        MenuItem.of("바비큐립", 1),
                        MenuItem.of("초코케이크", 2),
                        MenuItem.of("제로콜라", 1)
                )
        );

        Assertions.assertThat(orderList.toString()) // when
                .isEqualTo("티본스테이크 1개\n바비큐립 1개\n초코케이크 2개\n제로콜라 1개"); // then
    }
}
