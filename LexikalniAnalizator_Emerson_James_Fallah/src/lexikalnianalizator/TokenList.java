/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexikalnianalizator;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author st52501
 */
public class TokenList {

    private ArrayList<Token> list;

    public TokenList() {
        list = new ArrayList();
    }

    public void add(Token newToken) {
        if (list.isEmpty()
                || !(list.get(list.size() - 1) instanceof SeparatorToken)
                || list.get(list.size() - 1) instanceof SeparatorToken
                && !(newToken instanceof SeparatorToken && ((SeparatorToken) newToken).getValue() == "bílý znak")) {
            list.add(newToken);
        }
    }

    public String toString(int i) {
        return list.get(i).toString();
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }
    }
    
    public  ArrayList<Token> getTokenList(){
        return list;
    }

}
