/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import files.JSFile;
import files.JWFile;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Fran
 */
public class txtPlayer {

    public static void refreshTxt(Player player,File f){
        //checks for the console
        System.out.println("->Executing refreshTxt");
        
        Date date = new Date();
        SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");
        
        String texto;
        texto = "Ultimo jugador: "+player.name+"\n"+
                "Ultima modificacion: "+fDate.format(date);
        
        JWFile.writeTextFile(texto, f);
        
        //checks for the console
        System.out.println("....It has been replace the file "+f);
    }
    
    public static String[] getData (File f){
        //checks for the console
        System.out.println("->Executing getData");
        
        String data[] = new String[2];
        
        String namePlayer = JSFile.searchTextFile(f, "Ultimo jugador:");
        String modifiedDate = JSFile.searchTextFile(f, "Ultima modificacion:");
        
        int separator1 = namePlayer.indexOf("Ultimo jugador: ");
        data[0] = namePlayer.substring(16+separator1);
        
        int separator2 = namePlayer.indexOf("Ultima modificacion: ");
        data[1] = modifiedDate.substring(22+separator2);
        
        //checks for the console
        System.out.println("....The data of the txt,"+f+" are: "
                + "NAME-"+data[0]+" DATE-"+data[1]);
        
        return data;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File f = new File("H:\\Archivos de programa\\Star Wars Knight Old Republic\\Player.jrd");
        Player fran = new Player("Fran",f);
        
        refreshTxt(fran, f);
        String data [];
        data = getData(f);
        
        System.out.println("Los datos son");
        System.out.println(data[0]);
        System.out.println(data[1]);
    }
    
}
