/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Fran
 */
public class JCFile {
    //This class just contain the method copyFiles
    public static void copyDirectories (File d1, File d2){
        //checks for the console
        System.out.println("->Executing copyDirectories");
        
        if(!d1.isDirectory()){
            //We will search the files and then move them recursive
            copyFyles(d1, d2);
        }//end if directory
        else{
            //Copy d1 to d2, as they are files
            
            //Creating the directory d2 if it does not exist yet.
            if(!d2.exists()){
                d2.mkdir();
                System.out.println("Creating directory: "+d2);
            }//end if creating directori
            
            /*  1. Obtein the list of the existing files in the directory
                2. Call recursivily this method to copy each of the files           
            */
            System.out.println("Searching in the directory: "+d1);
            String files[] = d1.list();
            for(int i=0; i<files.length; i++)
                copyDirectories(new File(d1,files[i]), new File(d2,files[i]));
            System.out.println("We copied the files sucesfully");
        }//end else files
    }//end method copyFyles
    
    public static void copyFyles (File f1, File f2){
        //checks for the console
        System.out.println("->Executing copyFyles");
        
        try{
            System.out.println("Copying the file "+f1);
            InputStream in = new FileInputStream(f1);
            OutputStream out = new FileOutputStream(f2);
                
            byte[] buf = new byte[1024];
            int len;
              
            while ((len = in.read(buf)) > 0){
                out.write(buf, 0, len);
            }//end while reader
            in.close();
            out.close();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }//end try
    }//end method copyFyles
    
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        File dFran = new File("C:\\Users\\Fran\\Documents\\NetBeansProjects\\JCopyFiles\\Prueba\\Fran Save");
        File dJandro = new File("C:\\Users\\Fran\\Documents\\NetBeansProjects\\JCopyFiles\\Prueba\\Jandro Save");
        File dStorage = new File("C:\\Users\\Fran\\Documents\\NetBeansProjects\\JCopyFiles\\Prueba\\Storage");
        
        System.out.println("Escoga una carpeta que copiar:\n 1) Fran\n 2)Jandro");
        int sel = input.nextInt();
        switch(sel){
            case 1:
                copyDirectories(dFran, dStorage);
                System.out.println("Copiados los archivos desde Fran Save hasta Storage");
                break;
            case 2:
                copyDirectories(dJandro, dStorage);
                System.out.println("Copiados los archivos desde Jandro Save hasta Storage");
                break;
            default:
                System.err.println("Esa opcion no existe");
                break;
        }
    }//end main method
}//end class JCFiles
