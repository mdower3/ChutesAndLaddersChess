
import java.util.LinkedList;



/**
 *
 * @author max_d
 */
public class AI {
    
    int color;
    
    public int[] makeMove(Board board) {
        
        int [] reference = new int[3];
        
        MinimaxAlphaBeta test = new MinimaxAlphaBeta();
        reference = test.alphaBeta(board, 0, color, Double.MIN_VALUE, Double.MAX_VALUE, 3);
        
        System.out.println("This is the reference: " + reference[0] + "  " + reference[2]);
        board.move(board.board, reference[1], reference[2]);
        
        
        return reference;
    }
    
    
    public AI(int color) {
        this.color = color;
    }
}
