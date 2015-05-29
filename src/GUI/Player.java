/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

import java.io.File;

/**
 *
 * @author Fran
 */
public class Player {
    //fields
    String name;
    File playerFold;

    public Player(String name, File playerFold) {
        this.name = name;
        this.playerFold = playerFold;
    }

    public Player() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlayerFold(File playerFold) {
        this.playerFold = playerFold;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", playerFold=" + playerFold + '}';
    }

}
