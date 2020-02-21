
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author max_d
 */
public class VisualTile extends JPanel {
    
    int i, j;
    BoardDisplay board;

    public int getI() {
        return i;
    }

    

    public int getJ() {
        return j;
    }

    
    
    public VisualTile(int i, int j, BoardDisplay board) {
        this.j = j;
        this.i = i;
        this.board = board;
        
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                System.out.println(board.coordsToBoard(i, j));
                board.select(i, j);
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                
            }

            @Override
            public void mouseExited(MouseEvent me) {
                
            }
            
        });
        
    }
    
}
