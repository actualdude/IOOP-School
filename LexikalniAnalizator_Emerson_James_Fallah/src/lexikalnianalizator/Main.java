/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexikalnianalizator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author st52501
 */
public class Main {

    public static void main(String[] args) {
        try {
            LexikalniAnalizator lean = new LexikalniAnalizator("1.txt");
            lean.analizuj();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

//        try {
//            if (args.length > 0) {
//                LexikalniAnalizator lean = new LexikalniAnalizator(args[0]);
//                lean.analizuj();
//            } else {
//                throw new IOException("Soubor nenalezen!");
//            }
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//            ex.printStackTrace();
//        }
    }
}
