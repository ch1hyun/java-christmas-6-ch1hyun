package christmas.model;

import christmas.constants.EventConstant;
import christmas.constants.enums.MenuBoard;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class MenuItemTest {
    static Stream<Arguments> menuItemAndMenuBoardWithExpectedResult() {
        return Stream.of(
                Arguments.arguments(MenuItem.of("양송이수프", 1), MenuBoard.애피타이저, EventConstant.TRUE),
                Arguments.arguments(MenuItem.of("티본스테이크", 1), MenuBoard.메인, EventConstant.TRUE),
                Arguments.arguments(MenuItem.of("초코케이크", 1), MenuBoard.디저트, EventConstant.TRUE),
                Arguments.arguments(MenuItem.of("제로콜라", 1), MenuBoard.음료, EventConstant.TRUE)
        );
    }

    @ParameterizedTest
    @MethodSource("menuItemAndMenuBoardWithExpectedResult")
    @DisplayName("메뉴 아이템이 주어졌을 때 카테고리에 속하는지 판별할 수 있어야 합니다.")
    void 카테고리에_속하는지_테스트(MenuItem menuItem, MenuBoard menuBoard, Boolean expected) { // given
        Assertions.assertThat(menuItem.isGroup(menuBoard)) // when
                .isEqualTo(expected); // then
    }

    @Test
    @DisplayName("메뉴 아이템이 주어졌을 때 비용을 계산할 수 있어야 합니다.")
    void 비용_계산_테스트() {
        // given
        MenuItem menuItem = MenuItem.of("티본스테이크", 10);

        Assertions.assertThat(menuItem.getAmount()) // when
                .isEqualTo(550_000); // then
    }

    @Test
    @DisplayName("메뉴 아이템이 주어졌을 때 주문 개수를 반환할 수 있어야 합니다.")
    void 주문_개수_반환_테스트() {
        // given
        MenuItem menuItem = MenuItem.of("양송이수프", 17);

        Assertions.assertThat(menuItem.getCount()) // when
                .isEqualTo(17); // then
    }

    @Test
    @DisplayName("메뉴 아이템이 주어졌을 때 주문 메뉴의 이름을 반환할 수 있어야 합니다.")
    void 주문_메뉴_이름_반환_테스트() {
        // given
        MenuItem menuItem = MenuItem.of("해산물파스타", 4);

        Assertions.assertThat(menuItem.getName()) // when
                .isEqualTo("해산물파스타"); // then
    }

    static Stream<Arguments> menuItemAndExpectedPrints() {
        return Stream.of(
                Arguments.arguments(MenuItem.of("양송이수프", 1), "양송이수프 1개"),
                Arguments.arguments(MenuItem.of("티본스테이크", 5), "티본스테이크 5개")
        );
    }

    @ParameterizedTest
    @MethodSource("menuItemAndExpectedPrints")
    @DisplayName("메뉴 아이템이 주어졌을 때 플래너가 요구하는 형식으로 출력되어야 합니다.")
    void 반환_메시지_형식_테스트(MenuItem menuItem, String expected) { // given
        Assertions.assertThat(menuItem.toString()) // when
                .isEqualTo(expected); // then
    }
}
