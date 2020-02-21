
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
public class King implements Tile{

    int color;
    Boolean moved;
    
    @Override
    public String getName() {
        return "King";
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return color * 100;
    }

    @Override
    public void setMoved() {
        moved = true;
    }
    
    @Override
    public LinkedList<Integer> getMoves(Board board, int pos, LinkedList<ChuteLadder> chutesNLadders) {
        LinkedList<Integer> moves = new LinkedList();
        
        int temp;
        int [] possMoves = {-13, -12, -11,
                             -1,        1,
                             11,  12,  13};
        
        for(int i = 0; i < possMoves.length; i++) {
            if(!"Border".equals(board.board[pos + possMoves[i]].getName())) {
                
                if(board.board[pos + possMoves[i]].getColor() != color &&
                        !"Border".contains(board.board[pos + possMoves[i]].getName())) {
                
                moves.add(pos + possMoves[i]);
                }
            }
        
            
            
        }
        
        for(ChuteLadder chuteLadder : chutesNLadders) {
            if(moves.contains(chuteLadder.pos) && board.board[chuteLadder.endpoint].getColor() != color) {
                moves.remove(moves.indexOf(chuteLadder.pos));
                moves.add(chuteLadder.endpoint);
            }   
        }
        
        return moves;
    }
    
    
    public King(int color) {
        this.color = color;
        this.moved = false;
    }

    
    
}
