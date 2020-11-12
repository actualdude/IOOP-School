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
public enum Keywords {
    BEGIN("begin"), 
    TRY("try"), 
    WHILE("while"), 
    END("end"), 
    CATCH("catch");
    
    private final String wordname;

    private Keywords(String wordname) {
        this.wordname = wordname;
    }

    @Override
    public String toString() {
        return wordname;
    }
}
