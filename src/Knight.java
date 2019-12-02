
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
public class Knight implements Tile{

    int color;
    
    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return color * 3;
    }

    @Override
    public LinkedList<Integer> getMoves(Board board, int pos) {
        
        LinkedList<Integer> moves = new LinkedList();
        
        int [] possPos = {-25, -23, -14, -10,
                        10,  14,  23,  25};
        
        
        for(int i = 0; i < possPos.length; i++) {
            if(!"Border".equals(board.board[pos + possPos[i]].getName())) {
                
                if(board.board[pos + possPos[i]].getColor() != color &&
                        !"Border".contains(board.board[pos + possPos[i]].getName())) {
                
                moves.add(pos + possPos[i]);
                }
            }
        }
        return moves;
    }
    
    
    public Knight(int color) {
        this.color = color;
    }

    @Override
    public void setMoved() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
