public class Game {

    public static void main(String[] args) throws InterruptedException {

        System.out.println("You will never win, anyways,\nChoose a theme for the board: ");
        String symbol = Board.themePicker();

        System.out.println();
        System.out.println("Who plays first?\n1-Write 'Player' \n2-Write 'AI' \n");
        State.setTurn();

        while (Board.getBoardState() == 0) {
            Board.displayBoard(symbol);

            if (State.turn.equals("Player")) {
                MovesGenerator.makePlayerMove();
                Board.displayBoard(symbol);
            } else {
                MovesGenerator.makeAiMove();
                Board.displayBoard(symbol);
            }

            if (Board.getBoardState() != 0) {
                break;
            }
        }

        System.out.println(State.getWinnerState());
    }
}
