
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
public class Bishop implements Tile{

    int color;
    
    @Override
    public String getName() {
        return "Bishop";
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
    public LinkedList<Integer> getMoves(Board board, int pos, LinkedList<ChuteLadder> chutesNLadders) {
        LinkedList<Integer> moves = new LinkedList();
        
        int temp;
        int [] possMoves = {-13,      -11,
                           
                             11,       13};
        
        for(int i : possMoves) {
            
            temp = i;
            
            while(!"Border".equals(board.board[pos + temp].getName()) &&
                    board.board[pos + temp].getColor() != color) {
                
                moves.add(pos + temp);
                if(board.board[pos + temp].getColor() == color * -1) break;
                temp = temp + i;
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
    
    public Bishop(int color) {
        this.color = color;
    }

    @Override
    public void setMoved() {
        
    }
    
}
