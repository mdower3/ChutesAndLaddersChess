
import java.util.Iterator;
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
public class MinimaxAlphaBeta {

    
    int[] bestMove;
    int value;

    public int[] alphaBeta(Board currentBoard,
            int ply, int player, double alpha, double beta, int maxDepth) {

        System.out.println("The player on this fake move is: " + player);
        
        if (ply >= maxDepth) {

            
            return bestMove;
            
        } else {
            
            LinkedList<int[]> moveList = currentBoard.listMoves(currentBoard.board, player);
            if (moveList.isEmpty()) {
                return bestMove;
            }
            int [] bestMove = moveList.get(0);
            for (int i = 0; i < moveList.size(); i++) {
                int [] move = moveList.get(i);
                System.out.println("The move is: " + i);
                Board newBoard = new Board(currentBoard);
                newBoard.move(newBoard.board, move[1], move[2]);
                int[] tempMove = alphaBeta(newBoard, ply + 1, -player, -beta, -alpha, maxDepth);
                value = -player * newBoard.checkScore(newBoard.board);
                //move[0] = -tempMove[0];
                if (value > alpha) {
                    bestMove = move;
                    alpha = value;
                    if (alpha > beta) {
                        return bestMove;
                    }
                }
            }
            
            return bestMove;
        }
        
    }

}
