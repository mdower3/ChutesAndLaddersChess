
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
class Board {
    
    int length;
    Tile[] board;
    AI enemy1;
    AI enemy2;
    
    int turn;
    
    
    
    
    public LinkedList<int []> listMoves(int color) {
        LinkedList<int []> possMoves = new LinkedList();
        LinkedList<Integer> pieceMoves;
        int [] temp = new int[3];
        int temp1, temp2;
        
        for(int i = 26; i < (length * 12) - 26; i++) {
            if(board[i].getColor() == color) {
                
                pieceMoves = board[i].getMoves(this, i);
                
                
                    for(int j = 0; j < pieceMoves.size(); j++) {
                        
                        temp1 = board[i].getValue();
                        temp2 = pieceMoves.get(j);
                        temp[0] = temp1;
                        temp[1] = i;
                        temp[2] = temp2;
                        possMoves.add(temp);
                    }
            
                
            }
        }
        System.out.println("size of list:  " + possMoves.size());
        return possMoves;
    } 
    
    public boolean move(int oldSpot, int newSpot) {
        
        if(board[oldSpot].getMoves(this, oldSpot).contains(newSpot)) {
            board[newSpot]  = board[oldSpot];
            board[oldSpot] = new Empty();
            turn *= -1;
            
            if("Pawn".equals(board[newSpot].getName())) board[newSpot].setMoved();
            return true;
        }
        
        
        return false;
    }
    
    public void initStart() {
        
        
        board[26] = new Rook(-1);
        board[27] = new Knight(-1);
        board[28] = new Bishop(-1);
        board[29] = new Queen(-1);
        board[30] = new King(-1);
        board[31] = new Bishop(-1);
        board[32] = new Knight(-1);
        board[33] = new Rook(-1);
        for(int i = 38; i < 46; i++) {
            board[i] = new Pawn(-1);
        }
        
        board[(length * 12) + 14] = new Rook(1);
        board[(length * 12) + 15] = new Knight(1);
        board[(length * 12) + 16] = new Bishop(1);
        board[(length * 12) + 17] = new Queen(1);
        board[(length * 12) + 18] = new King(1);
        board[(length * 12) + 19] = new Bishop(1);
        board[(length * 12) + 20] = new Knight(1);
        board[(length * 12) + 21] = new Rook(1);
        for(int i = (length * 12) + 2; i < (length * 12) + 10; i++) {
            board [i] = new Pawn(1);
        }
        
    }
    
    @Override
    public String toString() {
        String result = "";
        
        for(int i = 26; i < (length * 12) + 22; i++) {
            if("Border".equals(board[i].getName()));
            else if(i % 12 == 11) result += "\n";
            else if(i % 12 == 0 ||
                    i % 12 == 1 ||
                    i % 12 == 10);
            else{         
            result += board[i].getValue() + " ";
            }
        }
        
        return result;
    }
    
    public String toStringBorders() {
        String result = "";
        
        for(int i = 0; i < board.length; i++) {
            
            if(i % 12 == 0) result += "\n";
            
                     
            result += board[i].getValue() + " ";
            
        }
        
        return result;
    }
    
    public Board(int length) {
        this.length = length;
        Tile [] board = new Tile[(length * 12) + 48];
        
        for(int i = 0; i < 24; i++) board[i] = new Border();
        for(int i = 24; i < (length * 12) + 24; i++) {
            if(i % 12 == 0 || i % 12 == 11 ||
               i % 12 == 1 || i % 12 == 10) {
                board[i] = new Border();
            } else {
                board[i] = new Empty();
            }
        }
        for(int i = (length * 12) + 24; i < (length * 12) + 48; i++) {
            board[i] = new Border();
        }
        
        this.board = board;
        turn = 1;
        enemy1 = new AI(-1);
        
        
        initStart();
    }
}
