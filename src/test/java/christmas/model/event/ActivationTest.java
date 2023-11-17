package christmas.model.event;

import christmas.constants.EventConstant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ActivationTest {
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("주어진 불리언 값을 가지고 반환할 수 있어야 합니다.")
    void 불리언_반환_테스트(Boolean test) {
        Assertions.assertThat(Activation.from(test).isActive()) // when
                .isEqualTo(test); // then
    }
}
