/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Fran
 */
public class Storage {
    private String name;
    private final int id = -1;
    private Path path;
    
    public Storage (Path path) {
        this.name = "Storage";
        this.path = path;       
    }
    
    public Storage (List<TokenContainer> tc) throws Exception {
        for(int i=0; i<tc.size(); i++) {
            if(tc.get(i).kind == TokenKind.STORAGE) {
                this.name = tc.get(i).name;
                this.path = Paths.get(tc.get(++i).name);
            }
        }
    }
    
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Path getPath() {
        return path;
    }

}
