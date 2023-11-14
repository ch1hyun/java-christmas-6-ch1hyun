package christmas.constants.enums;

public enum ErrorMessage {
    FORMAT("[ERROR] %s"),
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String content;

    ErrorMessage(String content) {
        this.content = content;
    }

    public String getMessage() {
        return Formatter.format(FORMAT.getContent(), this.getContent());
    }

    private String getContent() {
        return this.content;
    }
}
