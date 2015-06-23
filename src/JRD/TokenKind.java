/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 *
 * @author Fran
 */
public class TokenKind {
    static int PLAYER= 0;
    static int ID = 1;
    static int LASTTIME = 2;
    static int FILE = 3;
    static int STORAGE = 4;
    public static String[] list = {"player","id","lastTime","file","storage"};
    public static DateFormat DT = new SimpleDateFormat("HH:mm:SS '->' dd/MM/yyyy",
                        Locale.ENGLISH);
    
    public static int searchToken (String token) {
        for(int i=0; i<list.length; i++)
            if(list[i].equals(token)) return i;
        return -1;
    }
    
    public static String getKind (int i){
        if (i>=0)
            return list[i];
        else
            return "NoToken";
    }
}
