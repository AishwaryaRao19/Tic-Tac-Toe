import java.util.Scanner;

// The code begins here
public class Main {
    public static void main(String[] args) {

        System.out.print("Hello and welcome to Tic Tac Toe Game!");
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        // Check if the user wants to play on 3x3 or 4x4 or exit the game
        while(!exit) {

            System.out.println("\nChoose the game: \n1. 3x3 Tic Tac Toe \n2. 4x4 Tic Tac Toe \n3. Exit \n Response: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    String[][] board3x3 = {{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}};
                    System.out.println("Board for 3x3. Choose an input according to the numbers in the matrix.");
                    TicTacToe3x3.displayBoard(board3x3);
                    TicTacToe3x3 game3x3 = new TicTacToe3x3();
                    game3x3.startGame();
                    break;

                case 2:
                    String[][] board4x4 = {{"01", "02", "03", "04"}, {"05", "06", "07", "08"}, {"09", "10", "11", "12"}, {"13", "14", "15", "16"}};
                    System.out.println("Board for 4x4. Choose an input according to the numbers in the matrix.");
                    TicTacToe4x4.displayBoard(board4x4);
                    TicTacToe4x4 game4x4 = new TicTacToe4x4();
                    game4x4.startGame();
                    break;

                case 3:
                    exit = true;
                    break;

                default:
                    System.out.println("\nInvalid choice. Re-enter your choice");
                    break;
            }

        }

        System.out.println("Exiting the game.");

    }
}