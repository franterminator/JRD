/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class CreateJRD {
    
    public static void writeJRD (List<Player> players, Storage str, Path path)  {
        try {
            // Check
            System.out.println("->Refresh JRD");
            
            BufferedWriter bw = new BufferedWriter(new PrintWriter(path.toFile()));
            
            for(Player player: players) {
                bw.write(String.format("\n%s {\nid = %d;\nlastTime = %s;\nfile = \"%s\";\n}\n",
                        player.getName(),player.getId(),TokenKind.DT.format(player.getDate()),player.getPath()));
            }
            
            bw.write(String.format("\n%s {\nfile = %s;\n}\n",
                        str.getName(),str.getPath()));
            
            bw.close();
        } catch (NullPointerException | FileNotFoundException npe) {
            System.err.println("No se encontro la carpeta donde guardar el archivo.");
        } catch (IOException ex) {
            Logger.getLogger(CreateJRD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    
    
    
    
}
