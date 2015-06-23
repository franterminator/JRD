/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fran
 */
public class TokenJRD {
    
    /**
     * Obtein the tokens of a JRD file.
     * @param text
     * @return
     * @throws Exception 
     */
    public static List<TokenContainer> tokens (String text) throws Exception {
        List<TokenContainer> tokenList = new ArrayList<TokenContainer>();

        text = removeBlanks(text);
        
        int sBranch = text.indexOf("{");
        int fBranch = 0;
        while (sBranch>0){
            String name = text.substring(fBranch,sBranch);
            if(name.equals("Storage"))
                tokenList.add(new TokenContainer(text.substring(fBranch,sBranch),
                    TokenKind.STORAGE));
            else
                tokenList.add(new TokenContainer(text.substring(fBranch,sBranch),
                    TokenKind.PLAYER));
            
            fBranch = text.indexOf("}", sBranch) + 1;
            
            String[] temp = text.substring(sBranch+1,fBranch).split("[=;]");
            for(int i=0; i<temp.length-1;) {
                int kind = TokenKind.searchToken(temp[i++]);
                if(kind == TokenKind.FILE) {
                    int end = temp[i].length()-1; 
                    tokenList.add(new TokenContainer(temp[i++].substring(1,end),kind));
                }
                else
                    tokenList.add(new TokenContainer(temp[i++],kind));
            }
            
            
            sBranch = text.indexOf("{", fBranch);
            System.out.println(sBranch+"//"+fBranch);
        }
        
        return tokenList;
    }
    
    public static String removeBlanks (String text) {
        int start = 0;
        int end = text.indexOf("\"",start+1);
        boolean alternate = false;
        StringBuilder stb = new StringBuilder();
        System.out.println("Start: "+start+", end:"+end);
        while (!(start == -1 || end ==-1)) {
            if (alternate) {
                stb.append(text.substring(end,start));
                end = text.indexOf("\"",start+1);
                alternate = false;
            }
            else {
                stb.append(text.substring(start,end).replaceAll("\\s", ""));
                start = text.indexOf("\"",end+1);
                alternate = true;
            }
            if(end == -1)
                stb.append(text.substring(start,text.length()).replaceAll("\\s", ""));
            System.out.println("Start: "+start+", end:"+end);
        }
        System.out.println(stb.toString());
        return stb.toString();
    }
    
    /**
     * Read a file a convert all inside text in a string.
     * @param path
     * @param encoding
     * @return
     * @throws IOException 
     */
    public static String readFile(String path, Charset encoding) 
    throws IOException 
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        // Read files
        String texto = readFile("C:\\Users\\Fran\\Documents\\GitHub\\JCopyFiles\\Prueba\\Player.jrd",
                StandardCharsets.ISO_8859_1);
        System.out.println(texto);
        System.out.println("------------------");
        // TokenContainer and TokenKind
        List tokens = tokens(texto);
            for (Object token : tokens) {
                System.out.println(token);
            }
            
        // Players
        List<Player> players = Player.createPlayers(tokens);
            for (Player player:players) {
                System.out.printf("name: %s; id: %d; date: %s;\nfile: %s\n",
                        player.getName(),player.getId(),
                        TokenKind.DT.format(player.getDate()),player.getPath());
            }
        
        // Storage
        Storage str = new Storage(tokens);
            System.out.printf("name: %s; id: %d;\nfile: %s\n",
                        str.getName(),str.getId(),str.getPath());
        } catch(IOException io) {
            io.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(TokenJRD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
