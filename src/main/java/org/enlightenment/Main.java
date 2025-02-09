package org.enlightenment;

//player is always first
//player always puts X
//console always puts 0
//console chooses a random empty spot
//no OOP
//indexations of rows and cols start from 0

import java.util.Scanner;

public class Main {
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;
    private static String CELL_STATE_EMPTY = " ";
    private static String CELL_STATE_X = "X";
    private static String CELL_STATE_O = "O";

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[][] board = createBoard();

        System.out.println("Hello world!");
    }

    public static void startGameRound() {
        String[][] board = createBoard();
        startGameLoop(board);

        //start game loop
    }

    public static String[][] createBoard() {
        String[][] board = new String[ROW_COUNT][COL_COUNT];

        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++){
                board[row][col] = CELL_STATE_EMPTY;
            }
        }

        return board;
    }

    public static void startGameLoop(String[][] board) {
        //while (game not over)
        //playerTurn
        //consoleTurn
        //check Victory state (X_WON, O_WON, DRAW, GAME_NOT_OVER)
    }

    public static void makePlayerTurn() {
        // get input
        // validate input
        // place X on board
    }

    public static int[] inputBoardCoordinates() {
        System.out.println("Input coordinates including space (0-2): ");
        String[] input = scanner.nextLine().split(" ");

    }

    public static void makeConsoleTurn() {
        // get random empty cell
        // place 0 on board
    }

    public static void checkGameState() {
        // x = 1, 0 - (-1), empty = 0
        //count sums for rows, columns, diagonals

        //if sum.contains(3) -> X won
        //if sum.contains(-3) -> 0 won
        //if allCellsTaken() -> Draw
        //else -> game not over
    }

}