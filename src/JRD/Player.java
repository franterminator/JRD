/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author Fran
 */
public class Player {
    private String name;
    private int id;
    private Date date;
    private Path path;
    
    public Player (String name, int id, String date, Path path) throws Exception {
        this.name = name;
        this.id = id;

        this.date = TokenKind.DT.parse(date);

        this.path = path;
        
    }
    
    public Player (String name, int id) throws Exception {
        this.name = name;
        this.id = id;
        this.date = new Date();
        this.path = null;
    }
    
    public Player (List<TokenContainer> tc) throws Exception {
        if(tc.size()!=4) throw new IllegalArgumentException(
                "El token no cumple requisitos. Token.size="+tc.size());
        
        for(TokenContainer token : tc) {
            // Player
            if(token.kind == 0)
                this.name = token.name;
            // Id
            if(token.kind == 1)
                this.id = Integer.parseInt(token.name);
            // LastTime
            if(token.kind == 2) {
                DateFormat dt = new SimpleDateFormat("HH:mm:SS'->'dd/MM/yyyy",
                        Locale.ENGLISH);
                this.date = dt.parse(token.name);
            }
            // File
            if(token.kind == 3)
                this.path = Paths.get(token.name);
            
        }
    }

    public static List<Player> createPlayers (List<TokenContainer> tc) throws Exception {
        List<Player> players = new ArrayList<Player>();
        int start=0;
        boolean add = true;
        for(int i=0; ;i++) {
            // Llegamos al final, guardamos los jugadores encontrados
            if(i>=tc.size()) {
                
                if(add) {
                    players.add(new Player(tc.subList(start, i+1)));
                }
                break;
            }
            // Encontramos un nuevo jugador, guardamos los jugadores anteriores
            if(tc.get(i).kind == 0) {
                if (i>start) {
                    players.add(new Player(tc.subList(start, i)));
                    start = i;
                }
                add = true;
            }
            // Encontramos el bloque Storage, guardamos los jugadores encontrados
            if(tc.get(i).kind == 4) {
                if (i>start) {
                    players.add(new Player(tc.subList(start, i)));
                    start = i;
                }
                add = false; // Encontramos el bloque que pertenece a storage
                             // Tenemos que saltarnoslo, para ello un boolean
            }
            
            if (!add) start = i;
            
        }
            
        return players;
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Path getPath() {
        return path;
    }
    
    public void setCurrentTime() {
        date = new Date();
    }
    
    public void setPath (Path path) {
        this.path = path;
    }
    
}
