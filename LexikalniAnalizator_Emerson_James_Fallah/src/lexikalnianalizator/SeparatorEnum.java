
package lexikalnianalizator;

public enum SeparatorEnum {
    TABULATOR('\t', "bílý znak"),
    RETURN('\r', "bílý znak"),
    MEZERA(' ', "bílý znak"),
    ROVNASE('=', "rovná se"),
    CARKA(',', "čárka"),
    STREDNIK(';', "středník"),
    NEWLINE('\n', "new line");

    private final char character;
    private final String message;

    private SeparatorEnum(char character, String message) {
        this.character = character;
        this.message = message;
    }

    public char getChar() {
        return character;
    }

    public String getMessage() {
        return message;
    }
}
