
import java.util.Arrays;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author max_d
 */
class Board {

    int length;
    Tile[] board, fakeBoard;
    AI enemy1;
    AI enemy2;
    Boolean gameOver, inCheck;
    
    
    LinkedList<int[]> possMoves = new LinkedList();
    
    LinkedList<int[]> tempList = new LinkedList();
    
    int check;

    int turn;
    LinkedList<ChuteLadder> chutesNLadders;

    
    public int checkScore(Tile[] board) {
        int answer = 0;
        for(int i = 26; i < ((length + 4) * 12) - 26; i++) {
            answer += board[i].getValue();
        }
        return answer;
    }
    
    public LinkedList<int[]> listMoves(Tile[] board, int color) {
        
        if (check == turn && inCheck == false) {
            inCheck = true;
            
            return listCheckMoves(color);
        }
        
        possMoves.clear();
        LinkedList<Integer> pieceMoves;
        int[] temp = new int[3];
        int temp1, temp2;

        for (int i = 26; i < ((length + 4) * 12) - 26; i++) {
            if (board[i].getColor() == color) {

                pieceMoves = board[i].getMoves(board, i, chutesNLadders);

                for (int j = 0; j < pieceMoves.size(); j++) {

                    temp1 = board[j].getValue();
                    temp2 = pieceMoves.get(j);
                    temp[0] = temp1;
                    temp[1] = i;
                    temp[2] = temp2;
                    if(possMoves.isEmpty()) possMoves.add(temp);
                    else if(possMoves.getFirst()[0] <= temp[0]) possMoves.addFirst(temp);
                    else possMoves.add(temp);
                    
                    if ("King".equals(board[temp2].getName())) {
                        System.out.println(turn * -1 + " is in check");
                        
                        check = turn * -1;
                        if(listCheckMoves(turn * -1).isEmpty()) {
                            gameOver = true;
                            GameOver gameOver = new GameOver(turn, this);
                            gameOver.setVisible(true);
                        }

                    }
                    
                    
                }
            }
        }
        
        if(check != 0) return null;
        return possMoves;
    }

    public LinkedList<int[]> listCheckMoves(int color) {
        
        LinkedList<int[]> checkMoves = new LinkedList();
        possMoves.clear();
        
        LinkedList<Integer> pieceCheckMoves;
        
        int temp1, temp2;

        for (int i = 26; i < ((length + 4) * 12) - 26; i++) {
            if (board[i].getColor() == color) {
                
                pieceCheckMoves = board[i].getMoves(board, i, chutesNLadders);
                
                for (int j = 0; j < pieceCheckMoves.size(); j++) {
                    int[] tempo = new int[3];
                    check = 0;
                    temp1 = board[i].getValue();
                    temp2 = pieceCheckMoves.get(j);
                    tempo[0] = temp1;
                    tempo[1] = i;
                    tempo[2] = temp2;
                    fakeBoard = board.clone();
                    turn = color;
                    move(fakeBoard, tempo[1], tempo[2]);
                    listMoves(fakeBoard, turn);
                    
                    if(check == 0) {
                        checkMoves.add(tempo);
                        listMoves(fakeBoard, turn);
                        
                    }
                }
            }
        }
        turn = color;
        
        if (checkMoves.size() > 0) {
            
            return checkMoves;
        }
        
        gameOver = true;
        
        GameOver gameOver = new GameOver(turn, this);
        gameOver.setVisible(true);
        
        return null;
    }

    public boolean move(Tile[] board, int oldSpot, int newSpot) {
        System.out.println("score currently: " + this.checkScore(board));
        
        
        System.out.println(board[oldSpot].getClass());
        if (board[oldSpot].getMoves(board, oldSpot, chutesNLadders).contains(newSpot)) {
            board[newSpot] = board[oldSpot];
            board[oldSpot] = new Empty();
            
            if(turn == 1) listMoves(board, turn);
            
            
            this.turn *= -1;

            if ("Pawn".equals(board[newSpot].getName())) {
                board[newSpot].setMoved();
            }
            if ("King".equals(board[newSpot].getName())) {
                board[newSpot].setMoved();
            }
            if ("Rook".equals(board[newSpot].getName())) {
                board[newSpot].setMoved();
            }
            return true;
        }
        if(check != 0) listCheckMoves(turn);
        return false;
    }

    private boolean withinBoard(int space) {

        int x = space % 12 - 2;
        int y = (space - 2) / 12 - 2;

        
        if (x < 8 && x >= 0 && y < length - 2 && y > 1) {
            
            return true;
        }
        
        return false;

    }

    public void initStart() {

        board[26] = new Rook(-1);
        board[27] = new Knight(-1);
        board[28] = new Bishop(-1);
        board[29] = new Queen(-1);
        board[30] = new King(-1);
        board[31] = new Bishop(-1);
        board[32] = new Knight(-1);
        board[33] = new Rook(-1);
        for (int i = 38; i < 46; i++) {
            board[i] = new Pawn(-1);
        }

        board[(length * 12) + 14] = new Rook(1);
        board[(length * 12) + 15] = new Knight(1);
        board[(length * 12) + 16] = new Bishop(1);
        board[(length * 12) + 17] = new Queen(1);
        board[(length * 12) + 18] = new King(1);
        board[(length * 12) + 19] = new Bishop(1);
        board[(length * 12) + 20] = new Knight(1);
        board[(length * 12) + 21] = new Rook(1);
        for (int i = (length * 12) + 2; i < (length * 12) + 10; i++) {
            board[i] = new Pawn(1);
        }

    }

    
    public String toString(Tile [] board) {
        String result = "";

        for (int i = 26; i < (length * 12) + 22; i++) {
            if ("Border".equals(board[i].getName())); else if (i % 12 == 11) {
                result += System.lineSeparator();
            } else if (i % 12 == 0
                    || i % 12 == 1
                    || i % 12 == 10); else {
                result += board[i].getValue() + " ";
            }
        }

        return result;
    }

    public String toStringBorders() {
        String result = "";

        for (int i = 0; i < board.length; i++) {

            if (i % 12 == 0) {
                result += "\n";
            }

            result += board[i].getValue() + " ";

        }

        return result;
    }

    public Board(Board original) {
        
        this.inCheck = original.inCheck;
        this.board = original.board.clone();
        this.length = original.length;
        this.check = original.check;
        this.chutesNLadders = (LinkedList<ChuteLadder>) original.chutesNLadders.clone();
        this.enemy1 = original.enemy1;
        this.turn = original.turn;
        this.possMoves = (LinkedList<int[]>) original.possMoves.clone();
        this.gameOver = original.gameOver;
        this.tempList = (LinkedList<int[]>) original.tempList.clone();
        this.enemy2 = original.enemy2;
        
        
        System.arraycopy(original.board, 0, this.board, 0, original.board.length);
        
    }
    
    public Board(int length, int difficulty, int AIPlayers) {
        this.length = length;
        Tile[] board = new Tile[(length * 12) + 48];
        check = 0;
        for (int i = 0; i < 24; i++) {
            board[i] = new Border();
        }
        for (int i = 24; i < (length * 12) + 24; i++) {
            if (i % 12 == 0 || i % 12 == 11
                    || i % 12 == 1 || i % 12 == 10) {
                board[i] = new Border();
            } else {
                board[i] = new Empty();
            }
        }
        for (int i = (length * 12) + 24; i < (length * 12) + 48; i++) {
            board[i] = new Border();
        }

        //chutes and ladders go here
        chutesNLadders = new LinkedList();
        if (difficulty != 0) {
            int maxLadSli;
            if (difficulty == 1) {
                maxLadSli = (int) (Math.random() * (length - 4)) + 3;
            } else {
                maxLadSli = (int) (Math.random() * (length) + length);
            }
            
            int y, boardPos, type, rand, row, column;
            boolean occupied = false;
            ChuteLadder temp;

            for (int i = 0; i <= maxLadSli; i++) {

                do {
                    occupied = false;
                    rand = (int) (Math.random() * (8 * (length - 4)));
                    row = ((rand / 8) + 4) * 12;
                    column = rand % 8;
                    boardPos = row + column + 2;
                    
                    if (!withinBoard(boardPos)) {
                        occupied = true;
                    }

                    type = (int) (Math.random() * 7);
                    temp = new ChuteLadder(type, boardPos);

                    if (!withinBoard(temp.endpoint)) {
                        occupied = true;
                    }

                    if (!chutesNLadders.isEmpty()) {
                        for (ChuteLadder ladSli : chutesNLadders) {
                            if (boardPos == ladSli.getEndpoint()) {
                                occupied = true;
                            }
                            if (boardPos == ladSli.getPos()) {
                                occupied = true;
                            }
                            if (temp.endpoint < 50) {
                                occupied = true;
                            }

                            if (temp.endpoint > ((length) * 12) - 2) {
                                occupied = true;
                            }

                            if (temp.endpoint == ladSli.getEndpoint()) {
                                occupied = true;
                            }
                            if (temp.endpoint == ladSli.getPos()) {
                                occupied = true;
                            }
                        }
                    }

                } while (occupied == true);

                chutesNLadders.add(new ChuteLadder(type, boardPos));
            }

        }

        for (ChuteLadder chuteLadder : chutesNLadders) {
            System.out.println("From here: " + chuteLadder.pos + "  To here: " + chuteLadder.endpoint);
        }

        this.board = board;
        turn = 1;
        inCheck = false;
        if(AIPlayers == 1) enemy1 = new AI(-1);
        else if(AIPlayers == 0);
        else {
            enemy1 = new AI(-1);
            enemy2 = new AI(1);
        }
        initStart();

    }
}
