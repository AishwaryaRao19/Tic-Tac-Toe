import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// All the functionality for 3x3 Tic Tac Toe
public class TicTacToe3x3 {

    String[][] board = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

    public static String userSystem = "X";
    public static String computerSystem = "O";
    static Scanner sc = new Scanner(System.in);

    // display the current board
    public static void displayBoard(String[][] board) {
        Arrays.stream(board)
                .forEach(row -> {
                    System.out.print("\n|");
                    Arrays.stream(row)
                            .forEach(cell -> System.out.print(cell + " | "));
                });
    }

    /**
     * Add moves to board according to which player play's
     */
    public void addMove(int move, String system) {
        switch (move){
            case 1:
                board[0][0] = system;
                displayBoard(board);
                break;

            case 2:
                board[0][1] = system;
                displayBoard(board);
                break;

            case 3:
                board[0][2] = system;
                displayBoard(board);
                break;

            case 4:
                board[1][0] = system;
                displayBoard(board);
                break;

            case 5:
                board[1][1] = system;
                displayBoard(board);
                break;

            case 6:
                board[1][2] = system;
                displayBoard(board);
                break;

            case 7:
                board[2][0] = system;
                displayBoard(board);
                break;

            case 8:
                board[2][1] = system;
                displayBoard(board);
                break;

            case 9:
                board[2][2] = system;
                displayBoard(board);
                break;

            default:
                break;
        }
    }

    /**
     * Computer's move on the board
     * 1. Win if possible: When the computer can make a move that wins the game, it picks that move.
     * 2. Avoid imminent player win: If the player is winning in their next move, and it's prevented by the computer.
     * 3. Else, the computer picks a random (legal) move.
     */
    public void addComputerMove() {
        Random rand = new Random();
        int computerMove;

        // Check for a winning move for the computer
        int computerMoveWinComputer = getWinningMove(board, computerSystem);
        // Check for a move to block the user's winning move
        int computerMoveWinUser = getWinningMove(board, userSystem);

        while(true) {
            if (computerMoveWinComputer != -1 && isValidMove(board, computerMoveWinComputer)) {
                // Computer makes the winning move
                computerMove = computerMoveWinComputer;
                break;
            } else if (computerMoveWinUser != -1 && isValidMove(board, computerMoveWinUser)) {
                // Computer blocks the user's winning move
                computerMove = computerMoveWinUser;
                break;
            } else {
                // Computer makes a random move
                computerMove = rand.nextInt(9) + 1;
                if (isValidMove(board, computerMove)) {
                    break;
                }
            }
        }
        System.out.println("\nComputer's Move: ");
        addMove(computerMove, computerSystem);
    }

    /**
     * It checks for possible winning moves
     * @param board the current board
     * @param system tells which player
     * @return position for the move to be made
     */
    private static int getWinningMove(String[][] board, String system) {
        // Check possible wins in rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(system) && board[i][1].equals(system) && board[i][2].equals(" ")) {
                return boardNumberAssociation(i, 2);
            } else if (board[i][0].equals(system) && board[i][1].equals(" ") && board[i][2].equals(system)) {
                return boardNumberAssociation(i, 1);
            } else if (board[i][0].equals(" ") && board[i][1].equals(system) && board[i][2].equals(system)) {
                return boardNumberAssociation(i, 2);
            }
        }

        // Check possible wins in columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j].equals(system) && board[1][j].equals(system) && board[2][j].equals(" ")) {
                return boardNumberAssociation(2, j); // Place the move in the empty cell
            } else if (board[0][j].equals(system) && board[1][j].equals(" ") && board[2][j].equals(system)) {
                return boardNumberAssociation(1, j); // Place the move in the empty cell
            } else if (board[0][j].equals(" ") && board[1][j].equals(system) && board[2][j].equals(system)) {
                return boardNumberAssociation(0, j); // Place the move in the empty cell
            }
        }

        // Check possible wins in diagonal 1
        if (board[0][0].equals(system) && board[1][1].equals(system) && board[2][2].equals(" ")) {
            return boardNumberAssociation(2, 2);
        } else if (board[0][0].equals(system) && board[1][1].equals(" ") && board[2][2].equals(system)) {
            return boardNumberAssociation(1, 1);
        } else if (board[0][0].equals(" ") && board[1][1].equals(system) && board[2][2].equals(system)) {
            return boardNumberAssociation(0, 0);
        }

        // Check possible wins in diagonal 2
        if (board[0][2].equals(system) && board[1][1].equals(system) && board[2][0].equals(" ")) {
            return boardNumberAssociation(2, 0);
        } else if (board[0][2].equals(system) && board[1][1].equals(" ") && board[2][0].equals(system)) {
            return boardNumberAssociation(1, 1);
        } else if (board[0][2].equals(" ") && board[1][1].equals(system) && board[2][0].equals(system)) {
            return boardNumberAssociation(0, 2);
        }

        return -1; // No winning move found

    }

    /**
     * gets the position number using row and column
     * @param row row number
     * @param col column number
     * @return position on the board
     */
    private static int boardNumberAssociation(int row, int col){
        int[][] dummyBoard = new int[3][3];
        dummyBoard[0][0] = 1;
        dummyBoard[0][1] = 2;
        dummyBoard[0][2] = 3;

        dummyBoard[1][0] = 4;
        dummyBoard[1][1] = 5;
        dummyBoard[1][2] = 6;

        dummyBoard[2][0] = 7;
        dummyBoard[2][1] = 8;
        dummyBoard[2][2] = 9;

        return dummyBoard[row][col];
    }

    /**
     * User's move is selected and added to board
     * If the move is legal or not is checked
     */
    public void addUserMove() {
        while (true) {
            System.out.println("\nInput your value (1-9): ");
            int userInput = sc.nextInt();

            if (isValidMove(board, userInput)) {
                System.out.println("\nUser's Move: ");
                addMove(userInput, userSystem);
                break;
            } else {
                System.out.println("Invalid Move. Please enter a valid number (1-9).");
            }
        }
    }

    // Check of the move is legal or not
    private static boolean isValidMove(String[][] board, int position) {
        return switch (position) {
            case 1 -> board[0][0].equals(" ");
            case 2 -> board[0][1].equals(" ");
            case 3 -> board[0][2].equals(" ");
            case 4 -> board[1][0].equals(" ");
            case 5 -> board[1][1].equals(" ");
            case 6 -> board[1][2].equals(" ");
            case 7 -> board[2][0].equals(" ");
            case 8 -> board[2][1].equals(" ");
            case 9 -> board[2][2].equals(" ");
            default -> false;
        };
    }

    /**
     * It checks if the game is over
     * @param board the current board
     * @return boolean value if the game is over or not
     */
    public static boolean isGameOver(String[][] board) {
        // Computer win
        if(hasWon(board, computerSystem)){
            System.out.println("\n\nCOMPUTER WON!");
            return true;
        }

        // User win
        if (hasWon(board, userSystem)) {
            System.out.println("\n\nUSER WON!");
            return true;
        }

        // Draw
        if(isDraw(board)) {
            System.out.println("\nGame is a draw!");
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if the game is draw or not
     * it happens when no positions are empty
     * @param board the current board
     * @return boolean value
     */
    private static boolean isDraw(String[][] board){
        for (String[] row : board) {
            for (String col : row) {
                if (col.equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checks if there are any wins on the board
     * @param board the current board
     * @param system the current player
     * @return boolean value for the win
     */
    private static boolean hasWon(String[][] board, String system){
        return ((board[0][0].equals(system) && board[0][1].equals(system) && board[0][2].equals(system)) ||
                (board[1][0].equals(system) && board[1][1].equals(system) && board[1][2].equals(system)) ||
                (board[2][0].equals(system) && board[2][1].equals(system) && board[2][2].equals(system)) ||

                (board[0][0].equals(system) && board[1][0].equals(system) && board[2][0].equals(system)) ||
                (board[0][1].equals(system) && board[1][1].equals(system) && board[2][1].equals(system)) ||
                (board[0][2].equals(system) && board[1][2].equals(system) && board[2][2].equals(system)) ||

                (board[0][0].equals(system) && board[1][1].equals(system) && board[2][2].equals(system)) ||
                (board[0][2].equals(system) && board[1][1].equals(system) && board[2][0].equals(system)));
    }

    /**
     * the game begins from here
     */
    public void startGame() {
        var turn = 0;
        while(turn < 9) {
            addUserMove();
            if (isGameOver(board)){
                break;
            }

            addComputerMove();
            if (isGameOver(board)) {
                break;
            }
            turn += 2;
        }
    }


}
