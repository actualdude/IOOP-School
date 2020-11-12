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
public class IdentifierToken extends Token {

    private String ident;

    public IdentifierToken(TokenEnum tokenEnum, String ident) {
        super(tokenEnum.IDENT);
        this.ident = ident;

    }

    public String getIdent() {
        return ident;
    }

    public boolean isIdentifier(char character) {
        if (character >= 'a' && character <= 'z') {
            return true;
        } else {
            return false;
        }
    }

    public TokenEnum getType() {
        return TokenEnum.IDENT;
    }

    public String toString() {
        return ("IdentifierToken{" + ident + "}");
    }
}
