import java.util.Scanner;

public class State {

    final static int winState = 2;
    final static int loseState = -2;
    final static int drawState = 1;
    private static boolean endState = false;
    public static String turn = "Player";


    private static boolean threeEquals(char a, char b, char c) {
        return ((a == b) && (a == c) && (a != ' '));
    }

    public static char win() {
        //Checks win from rows.
        for (int row = 0; row < 3; row++) {
            if (threeEquals(Board.board[row][0], Board.board[row][1], Board.board[row][2]) && Board.board[row][0] != ' ') {
                return Board.board[row][0];
            }
        }

        //Checks win from Columns.
        for (int col = 0; col < 3; col++) {
            if (threeEquals(Board.board[0][col], Board.board[1][col], Board.board[2][col]) && Board.board[0][col] != ' ') {
                return Board.board[0][col];
            }
        }

        //Checks win from diagonals.
        if (threeEquals(Board.board[0][0], Board.board[1][1], Board.board[2][2]) && Board.board[0][0] != ' ') {
            return Board.board[0][0];
        }
        if (threeEquals(Board.board[0][2], Board.board[1][1], Board.board[2][0]) && Board.board[0][2] != ' ') {
            return Board.board[0][2];
        }

        //No wins
        return '-';
    }

    static int checkBoardState() {
        int gameState = 0;
        int win = win(); //Checks if a win exists.

        boolean endState;
        if (win != '-') {
            gameState = (win == Board.player ? loseState : winState);
            endState = true;

        } else {
            int emptyCells = 0;
            endState = false;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (Board.board[i][j] == ' ') {
                        emptyCells++;
                    }
                }
            }

            //Check for draw
            if (!endState && emptyCells == 0) {
                gameState = drawState;
                endState = true;
            } else {
                endState = false;
            }
        }
        return gameState;
    }

    public static String getWinnerState() {
        if (Board.getBoardState() == winState) {
            return "AI is taken over!";
        } else if (Board.getBoardState() == loseState) {
            return "You win!";
        } else {
            return "Draw!";
        }
    }

    public static void setTurn() {
        Scanner in = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("Player") && !choice.equals("AI")) {
            choice = in.nextLine();
            if (choice.equals("Player") || choice.equals("AI")) {
                break;
            }
            System.out.println("Invalid choice!");
        }
        turn = choice;
    }
}
