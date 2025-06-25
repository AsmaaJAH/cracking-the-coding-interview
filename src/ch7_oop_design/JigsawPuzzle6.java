/*
7.6 Jigsaw: Implement an NxN jigsaw puzzle. Design the data structures and explain an algorithm to
solve the puzzle. You can assume that you have a fi tsWi th method which, when passed two
puzzle edges, returns true if the two edges belong together.
 */
package ch7_oop_design;
import java.util.*;

public class JigsawPuzzle6 {

    //(could be flat, tab-in, or tab-out)
    public static class Edge {
        private final int id;

        public Edge(int id) {
            this.id = id;
        }

        public boolean fitsWith(Edge other) {
            return JigsawUtils.fitsWith(this, other);
        }

        public int getId() {
            return id;
        }
    }

    public static class PuzzlePiece {
        private final int id;
        private Edge top;
        private Edge bottom;
        private Edge left;
        private Edge right;

        public PuzzlePiece(int id, Edge top, Edge right, Edge bottom, Edge left) {
            this.id = id;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
            this.left = left;
        }

        public Edge getTop() { return top; }
        public Edge getRight() { return right; }
        public Edge getBottom() { return bottom; }
        public Edge getLeft() { return left; }

        public int getId() { return id; }

        // Rotate 90° clockwise
        public void rotate() {
            Edge temp = top;
            top = left;
            left = bottom;
            bottom = right;
            right = temp;
        }
    }

    public static class PuzzleBoard {
        private final int size;
        private PuzzlePiece[][] board;

        public PuzzleBoard(int size) {
            this.size = size;
            this.board = new PuzzlePiece[size][size];
        }

        public boolean placePiece(PuzzlePiece piece, int row, int col) {
            if (row > 0 && board[row - 1][col] != null) {
                if (!board[row - 1][col].getBottom().fitsWith(piece.getTop())) {
                    return false;
                }
            }
            if (col > 0 && board[row][col - 1] != null) {
                if (!board[row][col - 1].getRight().fitsWith(piece.getLeft())) {
                    return false;
                }
            }
            board[row][col] = piece;
            return true;
        }

        public void removePiece(int row, int col) {
            board[row][col] = null;
        }

        public PuzzlePiece[][] getBoard() {
            return board;
        }
    }

    public static class JigsawSolver {
        private final int size;
        private final List<PuzzlePiece> pieces;
        private final PuzzleBoard board;

        public JigsawSolver(List<PuzzlePiece> pieces, int size) {
            this.pieces = pieces;
            this.size = size;
            this.board = new PuzzleBoard(size);
        }

        public boolean solve() {
            return placeNextPiece(0, 0, new HashSet<>());
        }

        private boolean placeNextPiece(int row, int col, Set<Integer> used) {
            if (row == size) return true; 

            int nextRow = (col == size - 1) ? row + 1 : row;
            int nextCol = (col + 1) % size;

            for (PuzzlePiece piece : pieces) {
                if (used.contains(piece.getId())) continue;

                for (int r = 0; r < 4; r++) { // try all 4 rotations
                    if (board.placePiece(piece, row, col)) {
                        used.add(piece.getId());
                        if (placeNextPiece(nextRow, nextCol, used)) return true;
                        used.remove(piece.getId());
                        board.removePiece(row, col);
                    }
                    piece.rotate(); // try again
                }
            }

            return false;
        }

        public void printBoard() {
            for (PuzzlePiece[] row : board.getBoard()) {
                for (PuzzlePiece piece : row) {
                    System.out.print((piece != null ? piece.getId() : "-") + " ");
                }
                System.out.println();
            }
        }
    }

    public static class JigsawUtils {
        public static boolean fitsWith(Edge e1, Edge e2) {
            return e1.getId() + e2.getId() == 0; // ex, +1 fits -1
        }
    }
}
