/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package files;

import java.io.File;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *
 * @author Fran
 */
public class JDFile {
    
    public static void deleteDirectoryFiles (File d){
        //checks for the console
        System.out.println("->Executing deleteDirectory");
        
        
        if(!d.isDirectory()){
            deleteFile(d);
        }//end if the argument is a directory
        else{
            //checks for the console
            System.out.println("....Deleting directory: "+d);
            
            String files[] = d.list();
           
            for(int i=0; i<files.length; i++){
                deleteDirectoryFiles(new File(d,files[i]));
                try{
                    File directoryToDelete = new File(d,files[i]);
                    directoryToDelete.delete();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }//end for recursive call
        }
    }//end deleteDirectoryFiles method
    
    public static void deleteDirectoryPath (Path path){
        //checks for the console
        System.out.println("->Executing deleteDirectory");
        
        // Creating file
        File d = path.toFile();
        
        if(!d.isDirectory()){
            deleteFile(d);
        }//end if the argument is a directory
        else{
            //checks for the console
            System.out.println("....Deleting directory: "+d);
            
            String files[] = d.list();
           
            for(int i=0; i<files.length; i++){
                deleteDirectoryFiles(new File(d,files[i]));
                try{
                    File directoryToDelete = new File(d,files[i]);
                    directoryToDelete.delete();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                
            }//end for recursive call
        }
    }//end deleteDirectoryFiles method
    
    
    public static void deleteFile(File f){
        //checks for the console
        System.out.println("->Executing deleteFile");
        
        //this method delete files
        f.delete();
        System.out.println("....It has been removed: "+f);
    }
    
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        File d = new File("H:\\Archivos de programa\\Star Wars Knight Old Republic\\saves");
        
        System.out.println("Esta seguro que desea borrar los archivos?");
        System.out.println(" 1) Si");
        System.out.println(" 2) No");
        int sel = input.nextInt();
        switch(sel){
            case 1:
                deleteDirectoryFiles(d);
                break;
            case 2:
                System.out.println("---Los archivos no se han borrado");
                break;
            default:
                System.err.println("Esa opcion no existe motherfucker");
        }
    }
}//end class JDFile
