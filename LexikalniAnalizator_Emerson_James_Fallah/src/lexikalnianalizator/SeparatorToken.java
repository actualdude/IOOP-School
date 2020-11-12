/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexikalnianalizator;

/**
 *
 * @author st52501
 */
public class SeparatorToken extends Token {

    private String value;

    public SeparatorToken(TokenEnum tokenEnum, String value) {
        super(tokenEnum.SEPARATOR);
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public TokenEnum getType() {
        return TokenEnum.SEPARATOR;
    }

    public boolean isSeparator(char character) {
        for (int i = 0; i < SeparatorEnum.values().length; i++) {
            if (character == SeparatorEnum.values()[i].getChar()) {
                return true;
            }
        }

        return false;
    }

    public int getSeparatorIndex(char character) {
        for (int i = 0; i < SeparatorEnum.values().length; i++) {
            if (character == SeparatorEnum.values()[i].getChar()) {
                return i;
            }
        }
        
        return -1;
    }

    public String toString() {
        return "SeparatorToken{Separator{" + value + "}}";
    }

}
