package org.enlightenment;

//player is always first
//player always puts X
//console always puts 0
//console chooses a random empty spot
//no OOP
//indexations of rows and cols start from 0

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;
    private static String CELL_STATE_EMPTY = " ";
    private static String CELL_STATE_X = "X";
    private static String CELL_STATE_O = "O";
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        String[][] board = createBoard();

        board[0][0] = CELL_STATE_X;
        inputCellCoordinates(board);

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
        makePlayerTurn(board);

        //while (game not over)
        //playerTurn
        //consoleTurn
        //check Victory state (X_WON, O_WON, DRAW, GAME_NOT_OVER)
    }

    public static void makePlayerTurn(String[][] board) {
        int[] coordinates = inputCellCoordinates(board);
        board[coordinates[0]][coordinates[1]] = CELL_STATE_X;
        // place X on board
    }

    public static int[] inputCellCoordinates(String[][] board) {
        System.out.println("Input coordinates including space (0-2): ");

        do {
            //not checking if space exists and if the entered value is correct
            String[] input = scanner.nextLine().split(" ");

            int row = Integer.parseInt(input[0]);
            int col = Integer.parseInt(input[1]);

            if ((row < 0) || (row >= ROW_COUNT) || (col < 0) || (col >= COL_COUNT)) {
                System.out.println("Invalid coordinates! Please enter the coordinates from 0 to 2: ");
            } else if (!Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                System.out.println("This place is already taken!");
            } else {
                return new int[]{row, col};
            }
        } while (true);
    }

    public static int[] makeBotTurn(String[][] board) {
        do {
            int row = random.nextInt(ROW_COUNT);
            int col = random.nextInt(COL_COUNT);

            if (Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                return new int[]{row, col};
            }
        } while (true);
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