
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
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
public class ChuteLadder {

    int endpoint;
    int pos;
    int topLeft;
    int id;
    int length;
    JLabel label;

    public int getEndpoint() {
        return endpoint;
    }

    public int getPos() {
        return pos;
    }
    
    public int getTopRight() {
        return topLeft;
    }
    
    public int getId() {
        return id;
    }
    
    
    public ChuteLadder(int id, int pos) {
        this.pos = pos;
        endpoint = 0;
        this.id = id;
        switch (id) {
            
            case 0: endpoint = pos -23; // 2x3 ladder going right.
            topLeft = pos - 24;
            length = 3;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x3ToRight.png"));
            break;
            
            case 1: endpoint = pos +23; // 2x3 slide going left.
            topLeft = pos - 1;
            length = 3;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Slide2x3ToLeft.png"));
            break;
            
            case 2: endpoint = pos -25; // 2x3 ladder going left.
            topLeft = pos - 25;
            length = 3;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x3ToLeft.png"));
            break;
            
            case 3: endpoint = pos +25; // 2x3 slide facing right.
            topLeft = pos;
            length = 3;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Slide2x3ToRight.png"));
            break;
            
            case 4: endpoint = pos -35; // 2x4 ladder going right.
            topLeft = pos -36;
            length = 4;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x4ToRight.png"));
            break;
            
            case 5: endpoint = pos +35; //2x4 slide going left.
            topLeft = pos - 1;
            length = 4;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Slide2x4ToLeft.png"));
            break;
            
            case 6: endpoint = pos -37; // 2x4 ladder going left.
            topLeft = pos - 37;
            length = 4;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x4ToLeft.png"));
            break;
            
            case 7: endpoint = pos +37; // 2x4 slide going right.
            topLeft = pos;
            length = 4;
            label = new JLabel(new ImageIcon("Images/ChutesNLadders/Slide2x4ToRight.png"));
            break;
        }
    }
}
