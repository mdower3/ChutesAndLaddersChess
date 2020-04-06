/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;

/**
 *
 * @author max_d
 */
public class Empty implements Tile{

    @Override
    public String getName() {
        return "Empty";
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public LinkedList<Integer> getMoves(Tile [] board, int pos, LinkedList<ChuteLadder> chutesNLadders) {
        return null;
    }

    @Override
    public void setMoved() {
        
    }

    @Override
    public boolean getMoved() {
        return false;
    }
    
}
