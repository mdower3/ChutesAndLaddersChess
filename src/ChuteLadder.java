
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
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
    int topRight;
    int id;

    public int getEndpoint() {
        return endpoint;
    }

    public int getPos() {
        return pos;
    }
    
    public int getTopRight() {
        return topRight;
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
            topRight = pos - 24;
            break;
            
            case 1: endpoint = pos +23; // 2x3 slide going left.
            topRight = pos - 1;
            break;
            
            case 2: endpoint = pos -25; // 2x3 ladder going left.
            topRight = pos - 25;
            break;
            
            case 3: endpoint = pos +25; // 2x3 slide facing right.
            topRight = pos;
            break;
            
            case 4: endpoint = pos -33; // 2x4 ladder going right.
            topRight = pos -34;
            break;
            
            case 5: endpoint = pos +33; //2x4 slide going left.
            topRight = pos - 1;
            break;
            
            case 6: endpoint = pos -35; // 2x4 ladder going left.
            topRight = pos - 36;
            break;
            
            case 7: endpoint = pos +35; // 2x4 slide going right.
            topRight = pos;
            break;
        }
    }
}
