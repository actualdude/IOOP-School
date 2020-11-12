/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexikalnianalizator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author st52501
 */
public class LexikalniAnalizator {

    private String address;
    private TokenList list = new TokenList();
    private TokenList tokenList = new TokenList();

    private SeparatorToken separatorToken = new SeparatorToken(TokenEnum.SEPARATOR, "");
    private IdentifierToken identifierToken = new IdentifierToken(TokenEnum.IDENT, "");
    private KeyToken keyToken = new KeyToken(TokenEnum.KEY, "");
    private NumberToken numberToken = new NumberToken(TokenEnum.NUMBER, 0);

    public LexikalniAnalizator(String address) {
        this.address = address;
    }

    public void analizuj() throws FileNotFoundException, IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(address))) {
            String tmp = "";
            String tmp1 = "";
            char tmpChar;

            while ((tmpChar = (char) br.read()) != (char) -1) {
                tmp += tmpChar;

                if (separatorToken.isSeparator(tmpChar)) {
                    tmp1 = tmp.substring(0, tmp.length() - 1);
                    if (tmp.length() == 1) {
                        tokenList.add(new SeparatorToken(TokenEnum.SEPARATOR, SeparatorEnum.values()[separatorToken.getSeparatorIndex(tmpChar)].getMessage()));
                    } else {
                        if (tmp1.length() > 1) {
                            if (keyToken.isKeyword(tmp1)) {
                                tokenList.add(new KeyToken(TokenEnum.KEY, tmp1));
                            } else if (tmp.length() > 2 && tmp.substring(0, 2).equals("0x")) {
                                tokenList.add(new NumberToken(TokenEnum.NUMBER, Integer.parseInt(tmp1.substring(2, tmp1.length()), 16)));
                            } else if (numberToken.isNumber(tmp1.charAt(0))) {
                                tokenList.add(new NumberToken(TokenEnum.NUMBER, Integer.parseInt(tmp1)));
                            } else if (identifierToken.isIdentifier(tmp1.charAt(0))) {
                                tokenList.add(new IdentifierToken(TokenEnum.IDENT, tmp1));
                            }
                        }

                        tokenList.add(new SeparatorToken(TokenEnum.SEPARATOR, SeparatorEnum.values()[separatorToken.getSeparatorIndex(tmpChar)].getMessage()));
                    }
                    tmp = "";
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        tokenList.printList();
    }
}
