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
public class NumberToken extends Token {

    private int value;

    public NumberToken(TokenEnum tokenEnum, int value) {
        super(tokenEnum.NUMBER);
        this.value = value;
    }

    public TokenEnum getType() {
        return TokenEnum.NUMBER;
    }

    public boolean isNumber(char character) {
        if (character >= '0' && character <= '9') {
            return true;
        } else {
            return false;
        }
    }

    public String toString() {
        return "NumberToken{value=" + value + "}";
    }
}
