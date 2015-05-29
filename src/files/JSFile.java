/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Fran
 */
public class JSFile {
    public static String searchTextFile (File f, String buscar){
        //checks for the console
        System.out.println("->Executing searchTextFile");
        
        String texto="";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(f));
            while((texto = bf.readLine())!=null){
                if(texto.indexOf(buscar)!=-1)
                    break;
            }//end while reader
            bf.close();
            
            //check if the process is correct in the console
            System.out.println("....The line of the file is: \n"+texto);
        }catch (FileNotFoundException fnot){
            fnot.printStackTrace();
        }catch(IOException io){
            io.printStackTrace();
        }//end try and catch
        return texto;
    }
    
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        File f = new File("C:\\Users\\Fran\\Documents\\NetBeansProjects\\JCopyFiles\\Prueba\\Storage\\Player.jrd");
        
        System.out.println("Escriba su texto: ");
        String buscar = input.next();
        String textoFile = searchTextFile(f,buscar);       
        System.err.println("Su texto es: \n"+textoFile);
    }
    
}
