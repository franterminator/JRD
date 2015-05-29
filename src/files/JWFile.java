/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Fran
 */
public class JWFile {
    public static void writeTextFile (String texto, File f){
        try{
            //checks for the console
            System.out.println("->Executing writeTextFile");
            
            BufferedWriter wf = new BufferedWriter (new FileWriter(f));
            
            wf.write(texto);
            wf.close();
            System.out.println("....We rewrite the file "+f+", sucesfully.");
        }catch (IOException io){
            io.printStackTrace();
        }
    }//end method writeTextFile
    
}//end class JWFile
