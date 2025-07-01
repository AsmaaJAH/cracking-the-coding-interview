/*
 7.1 O Minesweeper: Design and implement a text-based Minesweeper game. Minesweeper is the classic
single-player computer game where an NxN grid has B mines (or bombs) hidden across the grid. The
remaining cells are either blank or have a number behind them. The numbers reflect the number of
bombs in the surrounding eight cells. The user then uncovers a cell. If it is a bomb, the player loses.
If it is a number, the number is exposed. If it is a blank cell, this cell and all adjacent blank cells (up to
and including the surrounding numeric cells) are exposed. The player wins when all non-bomb cells
are exposed. The player can also flag certain places as potential bombs. This doesn't affect game
play, other than to block the user from accidentally clicking a cell that is thought to have a bomb.
(Tip for the reader: if you're not familiar with this game, please play a few rounds on line first.)
 */
package ch7_oop_design;

public class Minesweeper10 {

    public enum GameState {
        ONGOING,
        WON,
        LOST
    }

    public static class Position {
        public final int row;
        public final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static class Cell {
        private boolean hasMine; //لغم
        private boolean isRevealed;// اتكشف
        private boolean isFlagged;
        private int adjacentMines;

        public Cell() {
            this.hasMine = false;
            this.isRevealed = false;
            this.isFlagged = false;
            this.adjacentMines = 0;
        }

        public boolean hasMine() { return hasMine; }
        public void setMine(boolean mine) { this.hasMine = mine; }

        public boolean isRevealed() { return isRevealed; }
        public void reveal() { this.isRevealed = true; }

        public boolean isFlagged() { return isFlagged; }
        public void toggleFlag() { this.isFlagged = !this.isFlagged; }

        public int getAdjacentMines() { return adjacentMines; }
        public void setAdjacentMines(int count) { this.adjacentMines = count; }
    }

    public static class Board {
        private final int size;
        private final int bombCount;
        private final Cell[][] grid;

        public Board(int size, int bombCount) {
            this.size = size;
            this.bombCount = bombCount;
            this.grid = new Cell[size][size];
            initializeBoard();
        }

        private void initializeBoard() {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    grid[i][j] = new Cell();
                }
            }
        }

        public int getSize() { return size; }
        public Cell getCell(int row, int col) { return grid[row][col]; }
        public boolean isInBounds(int row, int col) {
            return row >= 0 && row < size && col >= 0 && col < size;
        }

        public int getBombCount() {
            return bombCount;
        }

        // add methods later: placeMines, calculateAdjacentMineCounts, revealCells, etc.
    }

    // 🎮 Game controller
    public static class MinesweeperGame {
        private final Board board;
        private GameState state;

        public MinesweeperGame(int size, int bombCount) {
            this.board = new Board(size, bombCount);
            this.state = GameState.ONGOING;
        }

        public GameState getGameState() {
            return state;
        }

        public Board getBoard() {
            return board;
        }

        // to implement:
        // revealCell(Position pos), toggleFlag(Position pos), checkWinCondition(), etc.
    }
}

