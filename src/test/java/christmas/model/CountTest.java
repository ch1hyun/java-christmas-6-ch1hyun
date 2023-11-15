package christmas.model;

import christmas.constants.enums.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CountTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -2})
    @DisplayName("양의 정수 이외의 값은 에러를 발생시킵니다.")
    void 카운트_모델_생성_테스트(Integer test) {
        Assertions.assertThatThrownBy(() -> Count.from(test))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
