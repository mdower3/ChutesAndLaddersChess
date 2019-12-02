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
public class Pawn implements Tile {

    int color;
    Boolean moved;
    
    @Override
    public String getName() {
        return "Pawn";
    }

    @Override
    public int getColor() {
        return color;
    }

    @Override
    public int getValue() {
        return color * 1;
    }

    @Override
    public LinkedList<Integer> getMoves(Board board, int pos) {
        LinkedList<Integer> moves = new LinkedList();
        
        if(color == 1) {
            if("Empty".equals(board.board[pos - 24].getName()) && !moved) moves.add(pos - 24);
            if("Empty".equals(board.board[pos - 12].getName())) moves.add(pos - 12);
            if(board.board[pos - 13].getColor() == color * -1) moves.add(pos - 13);
            if(board.board[pos - 11].getColor() == color * -1) moves.add(pos - 11);
        } else {
            if("Empty".equals(board.board[pos + 24].getName()) && !moved) moves.add(pos + 24);
            if("Empty".equals(board.board[pos + 12].getName())) moves.add(pos + 12);
            if(board.board[pos + 13].getColor() == color * -1) moves.add(pos + 13);
            if(board.board[pos + 11].getColor() == color * -1) moves.add(pos + 11);
        }
        return moves;
    }
    
    public Pawn(int color) {
        this.color = color;
        this.moved = false;
    }

    @Override
    public void setMoved() {
        moved = true;
    }
    
}
