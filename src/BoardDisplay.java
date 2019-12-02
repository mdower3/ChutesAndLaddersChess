
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
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
public class BoardDisplay {
    
    
    
    private JFrame frame;
    final int WIDTH = 8;
    
    Boolean gameOver;
    
    Point oldReference;
    
    int x1, x2, y1, y2;

    JPanel[][] squares;
    
    Board board;
    
    LinkedList<Integer> oldMoves = new LinkedList();
    
    
    public int pointToIntCoord(Point reference) {
        int answer = 
                frame.getContentPane().getComponentAt(reference).getAccessibleContext().getAccessibleIndexInParent();
        
        return translateToBoard(answer);
    }
    
    public int translateToBoard(int answer) {
        
        int row = ((answer / 8) + 2) * 12;
        int column = answer % 8;
        
        answer = row + column + 2;
        
        return answer;
    }
    
    public int translateFromBoard(int answer) {
        
        answer = answer - 2;
        int temp = answer % 12;
        answer = ((answer / 12) - 2) * 8;
        
        answer = answer + temp;
        
        return answer;
    }
    
    public void deSelect() {
        int selected = translateFromBoard(pointToIntCoord(oldReference));
        if(((selected / 8) + (selected % 8)) % 2 == 0) {
            frame.getContentPane().getComponent(selected).setBackground(Color.WHITE);
        }
            else frame.getContentPane().getComponent(selected).setBackground(Color.GRAY);
        
        for(int i : oldMoves) {
            if(((i / 8) + (i % 8)) % 2 == 0) frame.getContentPane().getComponent(i).setBackground(Color.WHITE);
            else frame.getContentPane().getComponent(i).setBackground(Color.GRAY);
        }        
        
        
    }
    
    public boolean select(Point reference) {
        
        int coords = 
                frame.getContentPane().getComponentAt(reference).getAccessibleContext().getAccessibleIndexInParent();
        
        int boardCoords = pointToIntCoord(reference);
        
        if(oldReference != null) {
            deSelect();
        }
        
        
        if(oldMoves.contains(coords))return true;        
        
        oldMoves.clear();
        
        if (board.board[pointToIntCoord(reference)].getColor() != board.turn) return false;
        
        
        if(board.board[boardCoords].getMoves(board, boardCoords) != null) {
            frame.getContentPane().getComponentAt(reference).setBackground(Color.YELLOW);
            
            for (int i : board.board[boardCoords].getMoves(board, boardCoords)) {
                
                frame.getContentPane().getComponent(translateFromBoard(i)).setBackground(Color.YELLOW);
                frame.getContentPane().getComponent(translateFromBoard(i)).setBackground(Color.YELLOW);
                oldMoves.add(translateFromBoard(i));
            }
        }
        
        frame.getContentPane().getComponentAt(reference).setBackground(Color.YELLOW);
        
        oldReference = null;
        
        return false;
    }
    
    
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
        
        
    }
    
    
    public void moveIcon(int oldSpot, int newSpot) {
        
        
        
        if(squares[newSpot / 8][newSpot % 8].getComponentCount() != 0) {
            squares[newSpot / 8][newSpot % 8].remove(squares[newSpot / 8][newSpot % 8].getComponent(0));
        }
        
        squares[newSpot / 8][newSpot % 8].add(squares[oldSpot / 8][oldSpot % 8].getComponent(0));
        
        
        
        
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
        frame = new JFrame("Chutes and Ladders Chess");
        frame.setSize(WIDTH * 45, length * 45);
        frame.setLayout(new GridLayout(length, WIDTH));
        frame.setResizable(false);
        squares = new JPanel[length][WIDTH];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < WIDTH; j++) {
                squares[i][j] = new JPanel();
                
                if ((i + j) % 2 == 0) {
                    squares[i][j].setBackground(Color.white);
                } else {
                    squares[i][j].setBackground(Color.gray);
                }
                
                
                frame.add(squares[i][j]);
            }
        }



        squares[0][0].add(new JLabel(new ImageIcon("CorrectSize/RookB.png")));
        squares[0][1].add(new JLabel(new ImageIcon("CorrectSize/KnightB.png")));
        squares[0][2].add(new JLabel(new ImageIcon("CorrectSize/BishopB.png")));
        squares[0][3].add(new JLabel(new ImageIcon("CorrectSize/QueenB.png")));
        squares[0][4].add(new JLabel(new ImageIcon("CorrectSize/KingB.png")));
        squares[0][5].add(new JLabel(new ImageIcon("CorrectSize/BishopB.png")));
        squares[0][6].add(new JLabel(new ImageIcon("CorrectSize/KnightB.png")));
        squares[0][7].add(new JLabel(new ImageIcon("CorrectSize/RookB.png")));
        for (int i = 0; i <= 7; i++) {
            squares[1][i].add(new JLabel(new ImageIcon("CorrectSize/PawnB.png")));
        }

        squares[length - 1][0].add(new JLabel(new ImageIcon("CorrectSize/RookW.png")));
        squares[length - 1][1].add(new JLabel(new ImageIcon("CorrectSize/KnightW.png")));
        squares[length - 1][2].add(new JLabel(new ImageIcon("CorrectSize/BishopW.png")));
        squares[length - 1][3].add(new JLabel(new ImageIcon("CorrectSize/QueenW.png")));
        squares[length - 1][4].add(new JLabel(new ImageIcon("CorrectSize/KingW.png")));
        squares[length - 1][5].add(new JLabel(new ImageIcon("CorrectSize/BishopW.png")));
        squares[length - 1][6].add(new JLabel(new ImageIcon("CorrectSize/KnightW.png")));
        squares[length - 1][7].add(new JLabel(new ImageIcon("CorrectSize/RookW.png")));
        for (int i = 0; i <= 7; i++) {
            squares[length - 2][i].add(new JLabel(new ImageIcon("CorrectSize/PawnW.png")));
        }

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

                if (oldReference != null) {
                    deSelect();
                }

                Point reference = frame.getContentPane().getMousePosition();

                if (select(reference)) {
                    moveIcon(reference);
                    board.move(pointToIntCoord(oldReference), pointToIntCoord(reference));
                    oldReference = null;
                    oldMoves.clear();
                } else {
                    oldReference = reference;
                }

                if (board.turn == board.enemy1.color) {
                    int[] temp = board.enemy1.makeMove(board);
                    
                    System.out.println("index 0: " + temp[0]+ " index 1: " + temp[1]+ " index 2: " + temp[2]);
                    moveIcon(translateFromBoard(temp[1]), translateFromBoard(temp[2]));
                    
                }

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
