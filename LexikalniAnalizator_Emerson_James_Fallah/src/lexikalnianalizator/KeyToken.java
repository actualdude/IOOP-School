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
public class KeyToken extends Token {

    private String key;

    public KeyToken(TokenEnum tokenEnum, String key) {
        super(tokenEnum.KEY);
        this.key = key;
    }

    public boolean isKeyword(String tmp) {
        for (int i = 0; i < Keywords.values().length; i++) {
            if (Keywords.values()[i].toString().equals(tmp)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public TokenEnum getType() {
        return TokenEnum.KEY;
    }

    public String toString() {
        return ("KeyToken{klicoveSlovo=KeyWord{key=" + key + "}}");
    }
}
