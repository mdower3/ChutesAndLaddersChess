/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;


public interface Tile {
    
    public String getName();

    public int getColor();
    
    public int getValue();
    
    public void setMoved();
    
    public boolean getMoved();
    
    public LinkedList<Integer> getMoves(Tile [] board, int pos, LinkedList<ChuteLadder> chutesNLadders);
    
}
