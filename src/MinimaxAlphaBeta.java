
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
    int player;

    /*
    public int getPlayer() {
        return player;
    }
    
    public void setPlayer(int player) {
        this.player = player;
    }
    
    public int[] getBestMove() {
        return bestMove;
    }
    
    public void setBestMove(int[] bestMove) {
        this.bestMove = bestMove;
    }
    */
    public int [] alphaBeta(Board currentBoard,
            int ply, int player, double alpha, double beta, int maxDepth) {

        System.out.println("The player on this fake move is: " + player);
        
        
        
        if (ply >= maxDepth) {
            Board tempBoard;
            int bestScore = Integer.MIN_VALUE;
            LinkedList<int[]> moveList = currentBoard.listMoves(currentBoard.board, player);
            int [] tempMove, betterMove;
            betterMove = moveList.get(0);
            for (int i = 0; i < moveList.size(); i++) {
                tempBoard = new Board(currentBoard);
                tempMove = moveList.get(i);
                tempBoard.move(tempBoard.board, tempMove[1], tempMove[2]);
                if(tempBoard.checkScore(tempBoard.board) > bestScore) {
                    
                    betterMove = tempMove;
                    bestScore = tempBoard.checkScore(tempBoard.board);
                    betterMove[0] = tempBoard.checkScore(tempBoard.board);
                }  
            }
            return betterMove;
            
            
            
            
        } else {
            
            LinkedList<int[]> moveList = currentBoard.listMoves(currentBoard.board, player);
            if (moveList.isEmpty()) {
                
            }
            int [] bestMove = moveList.get(0);
            for (int i = 0; i < moveList.size(); i++) {
                int [] move = moveList.get(i);
                System.out.println("The move is: " + i);
                Board newBoard = new Board(currentBoard);
                newBoard.move(newBoard.board, move[1], move[2]);
                
                value = -alphaBeta(newBoard, ply + 1, -player, -beta, -alpha, maxDepth)[0];
               
                System.out.println("value is: " + value);
                
                if (value > alpha) {
                    System.out.println("Does this happen?");
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
