
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
public class Rook implements Tile {

    int color;
    boolean moved;
    
    @Override
    public String getName() {
        return "Rook";
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return color * 5;
    }

    @Override
    public LinkedList<Integer> getMoves(Board board, int pos) {
        LinkedList<Integer> moves = new LinkedList();
        
        int temp;
        int [] possMoves = {     -12,
                             -1,        1,
                                  12};
        
        for(int i : possMoves) {
            
            temp = i;
            
            while(!"Border".equals(board.board[pos + temp].getName()) &&
                    board.board[pos + temp].getColor() != color) {
                
                moves.add(pos + temp);
                if(board.board[pos + temp].getColor() == color * -1) break;
                temp = temp + i;
        }
            
            
        }
        
        
        return moves;
    }
    
    public Rook(int color) {
        this.color = color;
        moved = false;
    }

    @Override
    public void setMoved() {
        moved = true;
    }
    
}
