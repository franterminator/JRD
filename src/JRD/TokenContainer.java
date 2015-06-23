/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JRD;

/**
 *
 * @author Fran
 */
public class TokenContainer {
    String name;
    int kind;
    
    TokenContainer (String token, int tk){
        this.name = token;
        this.kind = tk;
    }
    
    @Override
    public String toString () {
        return "token: "+name+" \\kind: "+TokenKind.getKind(kind);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
