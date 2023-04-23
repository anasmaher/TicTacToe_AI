import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class MovesGenerator {

    private static Move findBestMove() {
        Move bestMove = new Move();
        int bestValue = Integer.MIN_VALUE;
        int tryValue;


        //Finding the path of the best value.
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (Board.board[row][col] == ' ') {
                    //Making the move.
                    Board.board[row][col] = Board.ai;

                    //Computing the value for the move.
                    tryValue = maxMin(100, false);

                    //Backtracking
                    Board.board[row][col] = ' ';

                    if (tryValue > bestValue) {
                        bestValue = tryValue;
                        bestMove.row = row;
                        bestMove.col = col;
                    }
                }
            }
        }

        return bestMove;
    }

    private static int maxMin(int depth, boolean isMaxing) {
        int gameState = Board.getBoardState();
        //Base case
        if (gameState != 0 || depth == 0) {
            return 100 * gameState + depth * gameState;
        }

        if (isMaxing) {
            int bestScore = Integer.MIN_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (Board.board[row][col] == ' ') {
                        //Making the move.
                        Board.board[row][col] = Board.ai;

                        //Getting path score.
                        int tryScore = maxMin(depth - 1, false);

                        //Backtracking
                        Board.board[row][col] = ' ';

                        //Updating best score
                        bestScore = Math.max(bestScore, tryScore);
                    }
                }
            }
            return bestScore;
        } else {
            int worstScore = Integer.MAX_VALUE;

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (Board.board[row][col] == ' ') {
                        Board.board[row][col] = Board.player;

                        int tryScore = maxMin(depth - 1, true);

                        Board.board[row][col] = ' ';

                        worstScore = Math.min(worstScore, tryScore);
                    }
                }
            }
            return worstScore;
        }
    }

    public static void makeAiMove() throws InterruptedException {
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Best move found!");
        TimeUnit.SECONDS.sleep(1);

        Move play = new Move();
        play = findBestMove();
        Board.board[play.row][play.col] = Board.ai;
        State.turn = "Player";
    }

    public static void makePlayerMove() {
        Scanner in = new Scanner(System.in);

        Move play = new Move();
        System.out.println("Number of row: ");
        play.row = in.nextInt();
        System.out.println("Number of column: ");
        play.col = in.nextInt();

        if (Board.board[play.row - 1][play.col - 1] == ' ') {
            Board.board[play.row - 1][play.col - 1] = Board.player;

            State.turn = "AI";
        } else {
            System.out.println("Invalid spot!");

            State.turn = "Player";
        }
    }
}
