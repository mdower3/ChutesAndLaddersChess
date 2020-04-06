
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
        //int highestValue = 0;
        //LinkedList<int[]> possMoves = board.listMoves(board.board, color);
        /*
        System.out.println();
        for (int i = 0; i < possMoves.size(); i++) {
            System.out.println(i);
            System.out.println(possMoves.get(i)[1] + " is where it is");
            System.out.println(possMoves.get(i)[2] + " is where it goes");
            System.out.println();
        }
        */
        /*
        for(int [] i : possMoves) {
            if(Math.abs(i[0]) > highestValue) {
                highestValue = i[0];
                reference = i;
            }
        }
        if(reference[2] != 0) board.move(board.board, reference[1], reference[2]);
        else {
            if(possMoves.size() == 1) reference = possMoves.get(0);
            else reference = possMoves.get(possMoves.size() / 2);
           board.move(board.board, reference[1], reference[2]);
        }
        */
        MinimaxAlphaBeta test = new MinimaxAlphaBeta();
        reference = test.alphaBeta(board, 0, color, Double.MIN_VALUE, Double.MAX_VALUE, 5);
        System.out.println("This is the reference: " + reference[0] + "  " + reference[2]);
        board.move(board.board, reference[1], reference[2]);
        
        
        return reference;
    }
    
    
    public AI(int color) {
        this.color = color;
    }
}
