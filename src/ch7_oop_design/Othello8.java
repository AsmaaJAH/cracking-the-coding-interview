/*
 * 
7.8 Othello: Othello is played as follows: Each Othello piece is white on one side and black on the other.
When a piece is surrounded by its opponents on both the left and right sides, or both the top and
bottom, it is said to be captured and its color is flipped. On your turn, you must capture at least one
of your opponent's pieces. The game ends when either user has no more valid moves. The win is
assigned to the person with the most pieces. Implement the object-oriented design for Othello.
 * 
 */
package ch7_oop_design;

import java.util.*;

public class Othello8 {

    enum Color { BLACK, WHITE }

    static class Position {
        int row, col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class Piece {
        Color color;
        public Piece(Color color) {
            this.color = color;
        }
        public Color getColor() {
            return color;
        }
        public void flip() {
            color = (color == Color.BLACK) ? Color.WHITE : Color.BLACK;
        }
    }

    static class Player {
        Color color;
        public Player(Color color) {
            this.color = color;
        }
        public Color getColor() {
            return color;
        }
    }

    static class Board {
        static final int SIZE = 8;
        private Piece[][] grid;
/*
The 8 possible directions around a cell on a 2D board:
Top-left	    {-1,-1}	
Top	            {-1, 0}	
Top-right	    {-1, 1}	
Left	        {0, -1}	
Right	        {0, 1}	
Bottom-left	    {1, -1}
Bottom	        {1, 0}	
Bottom-right	{1, 1}
 */
        private static final int[][] DIRECTIONS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            { 0, -1},          { 0, 1},
            { 1, -1}, { 1, 0}, { 1, 1}
        };

        public Board() {
            grid = new Piece[SIZE][SIZE];
            // Initialize center pieces
            grid[3][3] = new Piece(Color.WHITE);
            grid[3][4] = new Piece(Color.BLACK);
            grid[4][3] = new Piece(Color.BLACK);
            grid[4][4] = new Piece(Color.WHITE);
        }

        public boolean isValidMove(int row, int col, Color color) {
            if (!inBounds(row, col) || grid[row][col] != null) return false;
            return !getCapturedPieces(row, col, color).isEmpty();
        }

        public List<Position> getCapturedPieces(int row, int col, Color color) {
            List<Position> captured = new ArrayList<>();

            for (int[] dir : DIRECTIONS) {
                int r = row + dir[0], c = col + dir[1]; //ex: (row, col) = (4, 4)->Now (r, c) is a neighboring cell
                List<Position> temp = new ArrayList<>();
                while (inBounds(r, c) && grid[r][c] != null && grid[r][c].getColor() != color) {
                    temp.add(new Position(r, c));
                    r += dir[0];
                    c += dir[1];
                }
                if (inBounds(r, c) && grid[r][c] != null && grid[r][c].getColor() == color) {
                    captured.addAll(temp);
                }
            }

            return captured;
        }

        public boolean placePiece(int row, int col, Color color) {
            if (!isValidMove(row, col, color)) return false;

            List<Position> toFlip = getCapturedPieces(row, col, color);
            grid[row][col] = new Piece(color);
            for (Position p : toFlip) {
                grid[p.row][p.col].flip();
            }
            return true;
        }

        public boolean hasAnyValidMoves(Color color) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (isValidMove(i, j, color)) return true;
                }
            }
            return false;
        }

        public void printBoard() {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (grid[i][j] == null) System.out.print("- ");
                    else System.out.print(grid[i][j].getColor() == Color.BLACK ? "B " : "W ");
                }
                System.out.println();
            }
        }

        private boolean inBounds(int row, int col) {
            return row >= 0 && col >= 0 && row < SIZE && col < SIZE;
        }
    }

    static class OthelloGame {
        private Board board;
        private Player black;
        private Player white;
        private Player current;

        public OthelloGame() {
            board = new Board();
            black = new Player(Color.BLACK);
            white = new Player(Color.WHITE);
            current = black;
        }

        public void playGame() {
            Scanner sc = new Scanner(System.in);
            while (board.hasAnyValidMoves(Color.BLACK) || board.hasAnyValidMoves(Color.WHITE)) {
                board.printBoard();
                if (!board.hasAnyValidMoves(current.getColor())) {
                    System.out.println(current.getColor() + " has no valid moves. Turn skipped.");
                    switchPlayer();
                    continue;
                }

                System.out.println(current.getColor() + "'s turn. Enter row and column (0-7):");
                int row = sc.nextInt();
                int col = sc.nextInt();

                if (!board.placePiece(row, col, current.getColor())) {
                    System.out.println("Invalid move. Try again.");
                    continue;
                }

                switchPlayer();
            }
            sc.close();
            board.printBoard();
            System.out.println("Game over.");
        }

        private void switchPlayer() {
            current = (current == black) ? white : black;
        }
    }
}
