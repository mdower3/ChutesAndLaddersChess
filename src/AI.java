
import java.util.LinkedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author max_d
 */
public class AI {
    
    int color;
    
    public int[] makeMove(Board board) {
        
        int [] reference = new int[3];
        int highestValue = 0;
        LinkedList<int[]> possMoves = board.listMoves(color);
        
        for(int [] i : possMoves) {
            if(Math.abs(i[0]) > highestValue) {
                highestValue = i[0];
                reference = i;
            }
        }
        if(reference[2] != 0) board.move(reference[1], reference[2]);
        else {
            if(possMoves.size() == 1) reference = possMoves.get(0);
            else reference = possMoves.get(possMoves.size() / 2);
           board.move(reference[1], reference[2]);
        }
        
        
        
        
        return reference;
    }
    
    
    public AI(int color) {
        this.color = color;
    }
}
