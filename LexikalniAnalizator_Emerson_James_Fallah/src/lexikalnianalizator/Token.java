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
public class Token {
    TokenEnum tokenEnum;
    
    public TokenEnum getType(){
        return TokenEnum.NONE;
    }
     
    public Token(TokenEnum tokenEnum){
        this.tokenEnum = tokenEnum;
    }
            
    
    @Override
    public String toString(){
        return "No Token";
    }
}
