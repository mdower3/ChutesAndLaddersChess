import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
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
public class BoardDisplay {
    
    
    
    private JFrame frame;
    private JLayeredPane theBoard;
    final int WIDTH = 8;
    
    Boolean gameOver;
    
    Point oldReference;
    
    int  length, x, y, oldX, oldY, selectX, selectY;

    JPanel[][] squares;
    
    Board board;
    
    LinkedList<Point> oldMoves = new LinkedList();
    
    int [][] moves;
    
    Point point;
    
    
    public int pointToIntCoord(Point reference) {
        
        
        translateToCoord(reference);
        
        return translateToBoard();
    }
    
    public void translateToCoord(Point point) {
        x = point.x / 45;
        y = (point.y + 10) / 43;
    }
    
    public int translateToBoard() {
        
        System.out.println("this one is " + (x + ((y + 2) * 12) + 2));
        
        return x + ((y + 2) * 12) + 2;
    }
    
    public int translateFromBoard(int answer) {
        
        answer = answer - 2;
        int temp = answer % 12;
        x = temp;
        answer = ((answer / 12) - 2);
        y = answer;
        answer = answer * 8;
        answer = answer + temp;
        
        return answer;
    }
    
    public void intToRowAndColumn(int n) {
        x = (n) % 12 - 2;
        y = (n - 2) / 12 - 2;
        
        System.out.println("this one " + x + "  " + y);
    }
    
    
    
    public void deSelect() {
        //int selected = translateFromBoard(pointToIntCoord(oldReference));
        translateToCoord(oldReference);
        if((oldY + oldX) % 2 == 0) {
            squares[oldY][oldX].setBackground(Color.WHITE);
        }
            else squares[y][x].setBackground(Color.GRAY);
        
        for(Point i : oldMoves) {
            //System.out.println(i.x + " " + i.y);
            if((i.x + i.y) % 2 == 0) squares[i.y][i.x].setBackground(Color.WHITE);
            else squares[i.y][i.x].setBackground(Color.GRAY);
        }        
        oldMoves.clear();
        //System.out.println();
        
    }
    
    
    
    public boolean select(Point reference) {
        
        translateToCoord(reference);
        selectX = x;
        selectY = y;
        point = new Point(selectY, selectX);
        
        
        int boardCoords = pointToIntCoord(reference);
        
        if(oldReference != null) {
            deSelect();
        }
        
        if(oldMoves.contains(point))return true;        
        
        oldMoves.clear();
        
        oldMoves.add(point);
        if(board.board[boardCoords].getMoves(board, boardCoords) != null) {
            
            squares[selectY][selectX].setBackground(Color.YELLOW);
            oldReference = reference;
            for (int i : board.board[boardCoords].getMoves(board, boardCoords)) {
                System.out.println(i);
                translateFromBoard(i);
                squares[y][x].setBackground(Color.YELLOW);
                
                point = new Point(y, x);
                oldMoves.add(point);
            }
            System.out.println();
        }
        
        
        squares[selectY][selectX].setBackground(Color.YELLOW);
        oldX = selectX;
        oldY = selectY;
        
        System.out.println(oldX + " " + oldY);
        //oldReference = null;
        
        return false;
    }
    /*
    public JLabel checkSquares(Point reference) {
        
        for(int i = 0; i < length; i++) {
            for(int j = 0; j < WIDTH; j++) {
                if(squares[i][j] != null) { 
                    System.out.println(squares[i][j].getLocationOnScreen());
                    System.out.println(squares[i][j].getAccessibleContext().getAccessibleChild(0).getClass());
                    return (JLabel) squares[i][j].getAccessibleContext().getAccessibleChild(0);
                
                }
            }
        }
        
        
        
        return null;
    }
    */
    /*
    public void moveIcon(Point reference) {
        
        int oldSpot = 
                
                frame.getContentPane().getComponentAt(oldReference).getAccessibleContext().getAccessibleIndexInParent();
        
        int newSpot = 
                
                frame.getContentPane().getComponentAt(reference).getAccessibleContext().getAccessibleIndexInParent();
        
        
        
        if(squares[newSpot / 8][newSpot % 8].getComponentCount() != 0) {
            squares[newSpot / 8][newSpot % 8].remove(squares[newSpot / 8][newSpot % 8].getComponent(0));
        }
        
        squares[newSpot / 8][newSpot % 8].add(squares[oldSpot / 8][oldSpot % 8].getComponent(0));
        
        
        
        
        //NEED TO WORK HERE!!!!!
        
        
    }*/
    
    
    public void moveIcon() {
        
        if(squares[selectY][selectX].getComponentCount() != 0) {
            squares[selectY][selectX].remove(squares[selectY][selectX].getComponent(0));
        }
        
        squares[selectY][selectX].add(squares[oldY][oldX].getComponent(0));
        
        //NEED TO WORK HERE!!!!!
        
        
    }
    
    
    
    private void run() {
        /*while (!gameOver) {
            if (board.turn == board.enemy1.color) {
                int[] reference = board.enemy1.makeMove(board);
                moveIcon(reference[1], reference[2]);
            }
        }
        */
    }
    
    
    /**
     * Creates new form Board
     *
     * @param length
     */
    public BoardDisplay(int length, Board board) {
        //initComponents();
        this.board = board;
        this.length = length;
        frame = new JFrame("Chutes and Ladders Chess");
        frame.setSize(WIDTH * 45, length * 45);
        
        frame.setResizable(false);
        theBoard = new JLayeredPane();
        squares = new JPanel[length][WIDTH];
        frame.setContentPane(theBoard);
        
        GridBagLayout layout = new GridBagLayout();
        
        theBoard.setLayout(layout);
        
        int [] heights = new int[length];
        int [] widths = new int[WIDTH];
        Arrays.fill(heights, 45);
        Arrays.fill(widths, 45);
        layout.columnWidths = widths;
        layout.rowHeights = heights;
        
        Integer pieces = 0;
        
        //theBoard.getComponentsInLayer(0)
        
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = 1;
        c.gridwidth = 1;
        c.fill = 1;
        c.weightx = 1;
        c.weighty = 1;
        
        
        for (int i = 0; i < length; i++) {
            c.gridy = i;
            for (int j = 0; j < WIDTH; j++) {
                c.gridx = j;
                squares[i][j] = new JPanel();
                
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.white);
                } else {
                    squares[i][j].setBackground(Color.gray);
                }
                
                
                theBoard.add(squares[i][j], c);
                
            }
        }

        

        squares[0][0].add(new JLabel(new ImageIcon("Images/RookB.png")), c);
        squares[0][1].add(new JLabel(new ImageIcon("Images/KnightB.png")), c);
        squares[0][2].add(new JLabel(new ImageIcon("Images/BishopB.png")), c);
        squares[0][3].add(new JLabel(new ImageIcon("Images/QueenB.png")), c);
        squares[0][4].add(new JLabel(new ImageIcon("Images/KingB.png")), c);
        squares[0][5].add(new JLabel(new ImageIcon("Images/BishopB.png")), c);
        squares[0][6].add(new JLabel(new ImageIcon("Images/KnightB.png")), c);
        squares[0][7].add(new JLabel(new ImageIcon("Images/RookB.png")), c);
        for (int i = 0; i <= 7; i++) {
            squares[1][i].add(new JLabel(new ImageIcon("Images/PawnB.png")), c);
        }

        squares[length - 1][0].add(new JLabel(new ImageIcon("Images/RookW.png")), c);
        squares[length - 1][1].add(new JLabel(new ImageIcon("Images/KnightW.png")), c);
        squares[length - 1][2].add(new JLabel(new ImageIcon("Images/BishopW.png")), c);
        squares[length - 1][3].add(new JLabel(new ImageIcon("Images/QueenW.png")), c);
        squares[length - 1][4].add(new JLabel(new ImageIcon("Images/KingW.png")), c);
        squares[length - 1][5].add(new JLabel(new ImageIcon("Images/BishopW.png")), c);
        squares[length - 1][6].add(new JLabel(new ImageIcon("Images/KnightW.png")), c);
        squares[length - 1][7].add(new JLabel(new ImageIcon("Images/RookW.png")), c);
        for (int i = 0; i <= 7; i++) {
            squares[length - 2][i].add(new JLabel(new ImageIcon("Images/PawnW.png")), c);
        }

        //layout.addLayoutComponent("", new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x3ToRight.png")));
        
        c.gridx = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 3;
        
        JLabel ladder = new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x3ToRight.png"));
        theBoard.add(ladder, c);
        theBoard.setLayer(ladder, 1);
        
        
        //squares[5][5].add(ladder, new Integer(2));
        //theBoard.setLayer(ladder;
        
        frame.repaint();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.repaint();

        frame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                /*
                if (oldReference != null) {
                    deSelect();
                }
                */
                
                
                Point reference = theBoard.getMousePosition();
                
                
                translateToCoord(reference);
                //System.out.println(x + " " + y);
                
                
                
                if(select(reference)) {
                    
                    moveIcon();
                }
                
                /*
                
                //System.out.println(theBoard.getComponentAt(reference).getX());
                //squares[7][8].contains(reference);
                translateToCoord(reference);
                System.out.println(x + " " + y);
                
                if (select(reference)) {
                    //moveIcon(reference);
                    moveIcon();
                    board.move(pointToIntCoord(oldReference), pointToIntCoord(reference));
                    oldReference = null;
                    
                    System.out.println("move happened");
                }

                if (board.turn == board.enemy1.color) {
                    int[] temp = board.enemy1.makeMove(board);
                    
                    System.out.println("index 0: " + temp[0]+ " index 1: " + temp[1]+ " index 2: " + temp[2]);
                    
                    
                    translateFromBoard(temp[1]);
                    oldX = x;
                    oldY = y;
                    translateFromBoard(temp[2]);
                    selectX = x;
                    selectY = y;
                    moveIcon();
                    
                }
                */



                //squares[y][x].remove(squares[y][x].getComponent(0));
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        gameOver = false;
        run();

    }
}