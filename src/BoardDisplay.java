
import java.awt.Color;
import java.awt.GridBagConstraints;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.LINE_START;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.Arrays;
import java.util.LinkedList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import static javax.swing.JLayeredPane.DRAG_LAYER;
import static javax.swing.JLayeredPane.MODAL_LAYER;
import static javax.swing.JLayeredPane.POPUP_LAYER;
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

    int length, x, y, oldX, oldY, selectX, selectY;
    Color graySelect = new Color(180, 180, 50);
    Color whiteSelect = new Color(255, 255, 150);
    VisualTile[][] visualPieces;

    JPanel[][] tiles;

    Board board;

    LinkedList<Point> oldMoves = new LinkedList();
    LinkedList<JLabel> chutesNLadders = new LinkedList();
    // int [][] moves;

    Point point, oldPoint;

    public int coordsToBoard(int i, int j) {

        return ((i + 2) * 12) + j + 2;

    }

    public void intToRowAndColumn(int n) {
        x = (n) % 12 - 2;
        y = (n - 2) / 12 - 2;
    }

    public void deSelect() {
        //int selected = translateFromBoard(pointToIntCoord(oldReference));
        //translateToCoord(oldReference);
        if ((oldY + oldX) % 2 == 0) {
            tiles[oldY][oldX].setBackground(Color.WHITE);
        } else {
            tiles[oldY][oldX].setBackground(Color.GRAY);
        }

        for (Point i : oldMoves) {
            
            if ((i.x + i.y) % 2 == 0) {
                tiles[i.y][i.x].setBackground(Color.WHITE);
            } else {
                tiles[i.y][i.x].setBackground(Color.GRAY);
            }
        }
        oldMoves.clear();
        oldPoint = null;
        frame.repaint();
        

    }

    public boolean select(int i, int j) {

        selectY = i;
        selectX = j;
        point = new Point(j, i);

        if (oldMoves.contains(point)) {
            moveIcon();
            System.out.println("moving: " + oldY + " " + oldX);
            System.out.println("to: " + selectY + " " + selectX);
            board.move(coordsToBoard(oldY, oldX), coordsToBoard(selectY, selectX));

            if (board.turn == board.enemy1.color) {
                int[] temp = board.enemy1.makeMove(board);

                System.out.println("index 0: " + temp[0] + " index 1: " + temp[1] + " index 2: " + temp[2]);

                intToRowAndColumn(temp[1]);
                oldX = x;
                oldY = y;
                intToRowAndColumn(temp[2]);
                selectX = x;
                selectY = y;
                moveIcon();

                selectX = i;
                selectY = j;
                
            }

            return true;
        }

        if (oldPoint != null) {
            deSelect();
        }

        oldMoves.clear();

        if (board.board[coordsToBoard(i, j)]
                .getMoves(board, coordsToBoard(i, j), board.chutesNLadders) != null
                && board.board[coordsToBoard(i, j)].getColor() == 1) {

            tiles[i][j].setBackground(graySelect);
            if ((i + j) % 2 == 0) {
                tiles[i][j].setBackground(whiteSelect);
            } else {
                tiles[i][j].setBackground(graySelect);
            }

            for (int z : board.board[coordsToBoard(i, j)]
                    .getMoves(board, coordsToBoard(i, j), board.chutesNLadders)) {
                
                /*
                for(ChuteLadder chuteLadder: board.chutesNLadders) {
                    if(z == chuteLadder.endpoint) {
                        
                    }
                }
                */
                
                intToRowAndColumn(z);
                System.out.println("possibleMoves x: " + x + "  y: " + y);
                point = new Point(x, y);
                oldMoves.add(point);
                if ((x + y) % 2 == 0) {
                    tiles[y][x].setBackground(whiteSelect);
                } else {
                    tiles[y][x].setBackground(graySelect);
                }

            }
        }

        oldX = j;
        oldY = i;
        oldPoint = new Point(j, i);

        frame.repaint();
        return false;
    }

    public void moveIcon() {

        if (visualPieces[selectY][selectX].getComponentCount() != 0) {
            visualPieces[selectY][selectX].remove(visualPieces[selectY][selectX].getComponent(0));
        }

        visualPieces[selectY][selectX].add(visualPieces[oldY][oldX].getComponent(0));

        for (JLabel chuteNLadder : chutesNLadders) {
            theBoard.setLayer(chuteNLadder, POPUP_LAYER);
        }

        theBoard.setLayer(visualPieces[selectY][selectX], DRAG_LAYER);
        deSelect();
        frame.repaint();
        //NEED TO WORK HERE!!!!!

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
        visualPieces = new VisualTile[length][WIDTH];
        tiles = new JPanel[length][WIDTH];
        frame.setContentPane(theBoard);

        GridBagLayout layout = new GridBagLayout();

        theBoard.setLayout(layout);

        int[] heights = new int[length];
        int[] widths = new int[WIDTH];
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
                visualPieces[i][j] = new VisualTile(i, j, this);
                tiles[i][j] = new JPanel();
                if ((i + j) % 2 == 0) {
                    tiles[i][j].setBackground(Color.white);
                } else {
                    tiles[i][j].setBackground(Color.gray);
                }

                tiles[i][j].add(new JLabel(), c);

                visualPieces[i][j].setOpaque(false);
                theBoard.add(visualPieces[i][j], c);
                theBoard.add(tiles[i][j], c);
                theBoard.setLayer(tiles[i][j], MODAL_LAYER);
                theBoard.setLayer(visualPieces[i][j], POPUP_LAYER);
            }
        }
        
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.gridheight = 3;
        c.anchor = LINE_START;
        
        

        for (ChuteLadder chuteLadder : board.chutesNLadders) {

            c.weightx = 0;
            c.weighty = 0;
            
            c.gridx = (chuteLadder.topLeft - 2) % 12;
            c.gridy = (chuteLadder.topLeft - 2) / 12 - 2;
            c.gridwidth = 2;
            c.gridheight = chuteLadder.length;
            if(chuteLadder.label != null) {
                chutesNLadders.add(chuteLadder.label);
                theBoard.add(chuteLadder.label, c);
                theBoard.setLayer(chuteLadder.label, POPUP_LAYER);
            }
        }

        c.gridwidth = 1;
        c.gridwidth = 1;
        c.fill = 1;
        c.weightx = 1;
        c.weighty = 1;
        c.anchor = CENTER;

        visualPieces[0][0].add(new JLabel(new ImageIcon("Images/RookB.png")), c);
        visualPieces[0][1].add(new JLabel(new ImageIcon("Images/KnightB.png")), c);
        visualPieces[0][2].add(new JLabel(new ImageIcon("Images/BishopB.png")), c);
        visualPieces[0][3].add(new JLabel(new ImageIcon("Images/QueenB.png")), c);
        visualPieces[0][4].add(new JLabel(new ImageIcon("Images/KingB.png")), c);
        visualPieces[0][5].add(new JLabel(new ImageIcon("Images/BishopB.png")), c);
        visualPieces[0][6].add(new JLabel(new ImageIcon("Images/KnightB.png")), c);
        visualPieces[0][7].add(new JLabel(new ImageIcon("Images/RookB.png")), c);
        for (int i = 0; i <= 7; i++) {
            visualPieces[1][i].add(new JLabel(new ImageIcon("Images/PawnB.png")), c);
        }

        visualPieces[length - 1][0].add(new JLabel(new ImageIcon("Images/RookW.png")), c);
        //visualPieces[length - 1][1].add(new JLabel(new ImageIcon("Images/KnightW.png")), c);
        visualPieces[length - 1][2].add(new JLabel(new ImageIcon("Images/BishopW.png")), c);
        visualPieces[length - 1][3].add(new JLabel(new ImageIcon("Images/QueenW.png")), c);
        visualPieces[length - 1][4].add(new JLabel(new ImageIcon("Images/KingW.png")), c);
        visualPieces[length - 1][5].add(new JLabel(new ImageIcon("Images/BishopW.png")), c);
        visualPieces[length - 1][6].add(new JLabel(new ImageIcon("Images/KnightW.png")), c);
        visualPieces[length - 1][7].add(new JLabel(new ImageIcon("Images/RookW.png")), c);

        JLabel temp;
        for (int i = 0; i <= 7; i++) {
            //visualPieces[length - 2][i].add(new JLabel(new ImageIcon("Images/PawnW.png")), c);
            temp = new JLabel(new ImageIcon("Images/PawnW.png"));
            visualPieces[length - 2][i].add(temp, c);
            theBoard.setLayer(temp, 3);
        }

        temp = new JLabel(new ImageIcon("Images/KnightW.png"));
        visualPieces[length - 1][1].add(temp, c);
        theBoard.setLayer(temp, 1);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (visualPieces[i][j].getAccessibleContext().getAccessibleChildrenCount() != 0) {
                    theBoard.setLayer((JLabel) visualPieces[i][j].getComponent(0), -1);
                    
                }
            }
        }
        //layout.addLayoutComponent("", new JLabel(new ImageIcon("Images/ChutesNLadders/Ladder2x3ToRight.png")));

        //squares[5][5].add(ladder, new Integer(2));
        //theBoard.setLayer(ladder;
        frame.repaint();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.repaint();

        gameOver = false;

    }
}
