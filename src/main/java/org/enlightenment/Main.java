package org.enlightenment;

//player is always first
//player always puts X
//console always puts 0
//console chooses a random empty spot
//no OOP
//indexations of rows and cols start from 0

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final int ROW_COUNT = 3;
    private static final int COL_COUNT = 3;
    private static String CELL_STATE_EMPTY = " ";
    private static String CELL_STATE_X = "X";
    private static String CELL_STATE_O = "O";
    private static String GAME_STATE_X_WON = "Player X won!";
    private static String GAME_STATE_O_WON = "Player O won!";
    private static String GAME_STATE_DRAW = "Draw!";
    private static String GAME_STATE_NOT_FINISHED = "Game is not over yet!";
    private static Scanner scanner = new Scanner(System.in);
    private static Random random = new Random();

    public static void main(String[] args) {
        startGameRound();
    }

    public static void startGameRound() {
        String[][] board = createBoard();
        startGameLoop(board);
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
        boolean playerTurn = true;

        do {
            if (playerTurn) {
                makePlayerTurn(board);
                printBoard(board);
            } else {
                makeBotTurn(board);
                printBoard(board);
            }

            playerTurn = !playerTurn;

            System.out.println();

            String gameState = checkGameState(board);

            if (!Objects.equals(gameState, GAME_STATE_NOT_FINISHED)) {
                System.out.println(gameState);
                return;
            }
        } while (true);
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

    public static void makeBotTurn(String[][] board) {
        System.out.println("Bot's turn: ");
        int[] coordinates = getRandomEmptyCellCoordinates(board);
        board[coordinates[0]][coordinates[1]] = CELL_STATE_O;
    }

    public static int[] getRandomEmptyCellCoordinates(String[][] board) {
        do {
            int row = random.nextInt(ROW_COUNT);
            int col = random.nextInt(COL_COUNT);

            if (Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                return new int[]{row, col};
            }
        } while (true);
    }

    public static String checkGameState(String[][] board) {
        ArrayList<Integer> sums = new ArrayList<>();
        //iterate rows
        for (int row = 0; row < ROW_COUNT; row++) {
            int rowSum = 0;
            for (int col = 0; col < COL_COUNT; col++) {
                rowSum += calculateScore(board[row][col]);
            }
            sums.add(rowSum);
        }
        //iterate columns
        for (int col = 0; col < COL_COUNT; col++) {
            int colSum = 0;
            for (int row = 0; row < ROW_COUNT; row++) {
                colSum += calculateScore(board[row][col]);
            }
            sums.add(colSum);
        }
        //diagonal from top left to bottom right
        int leftDiagonalSum = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            leftDiagonalSum += calculateScore(board[i][i]);
        }
        sums.add(leftDiagonalSum);
        
        //diagonal from top right to top left
        int rightDiagonalSum = 0;
        for (int i = 0; i < ROW_COUNT; i++) {
            rightDiagonalSum += calculateScore(board[i][(ROW_COUNT - 1)- i]);
        }
        sums.add(rightDiagonalSum);

        if (sums.contains(3)) {
            return GAME_STATE_X_WON;
        } else if (sums.contains(-3)) {
            return GAME_STATE_O_WON;
        } else if (areAllCellsTaken(board)) {
            return GAME_STATE_DRAW;
        } else return GAME_STATE_NOT_FINISHED;
    }

    private static int calculateScore(String cellState) {
        if (Objects.equals(cellState, CELL_STATE_X)){
            return 1;
        }
        else if (Objects.equals(cellState, CELL_STATE_O)){
            return -1;
        } else {
            return 0;
        }
    }

    public static boolean areAllCellsTaken(String[][] board) {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int col = 0; col < COL_COUNT; col++) {
                if (Objects.equals(board[row][col], CELL_STATE_EMPTY)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void printBoard(String[][] board) {
        System.out.println("---------");
        for (int row = 0; row < ROW_COUNT; row++) {
            String line = "| ";
            for (int col = 0; col < COL_COUNT; col++) {
                line += board[row][col] + " ";
            }
            line += "|";
            System.out.println(line);
        }
        System.out.println("---------");
    }
}