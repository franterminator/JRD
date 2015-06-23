/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import static JRD.TokenJRD.readFile;
import static JRD.TokenJRD.tokens;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

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
            
            bw.write(String.format("\n%s {\nfile = \"%s\";\n}\n",
                        str.getName(),str.getPath()));
            
            bw.close();
            
        } catch (NullPointerException | FileNotFoundException npe) {
            System.err.println("No se encontro la carpeta donde guardar el archivo.");
        } catch (IOException ex) {
            System.err.println("Problemas a la hora de escribir en el archivo.");
        }
        
        
    }
    
    public static void main (String[] args) {
        try{
        // Read files
        String texto = readFile("C:\\Users\\Fran\\Documents\\GitHub\\JCopyFiles\\Prueba\\Player.jrd",
                StandardCharsets.ISO_8859_1);
        // TokenContainer and TokenKind
        List tokens = tokens(texto);
        // Players
        List<Player> players = Player.createPlayers(tokens);
        // Storage
        Storage str = new Storage(tokens);
        // Create JRD
        String path = new File("").getAbsolutePath();
        path = path.concat("\\Prueba\\JRD\\Prueba.jrd");
        System.out.println(path);
        writeJRD(players, str, Paths.get(path));
        
        } catch(IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(TokenJRD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
}
