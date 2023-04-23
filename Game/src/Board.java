import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Board {
    public static char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    public final static char player = 'X';
    public final static char ai = 'O';

    public static void displayBoard(String sym) {
        String symbol = sym;
        String line = "";

        System.out.println();
        for (int i = 0; i <= 8; i++) {
            System.out.print(symbol);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (board[i][j]) {
                    case ' ' -> line = " " + symbol + " ";
                    case player -> line = " X ";
                    case ai -> line = " O ";
                }
                System.out.print(line);
            }
            System.out.println();
        }
        for (int z = 0; z <= 8; z++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    public static int getBoardState() {
        return State.checkBoardState();
    }

    public static String themePicker() throws InterruptedException {
        String[] themes = {"*", "|", "+", "=", "$"};
        String line = "";

        for (int s = 0; s < 5; s++) {
            System.out.println();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("(" + (s + 1) + ")");
            for (int i = 0; i <= 8; i++) {
                System.out.print(themes[s]);
            }
            System.out.println();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    switch (board[i][j]) {
                        case ' ' -> line = " " + themes[s] + " ";
                        case player -> line = " X ";
                        case ai -> line = " O ";
                    }
                    System.out.print(line);
                }
                System.out.println();
            }
            for (int z = 0; z <= 8; z++) {
                System.out.print(themes[s]);
            }
            System.out.println();
        }

        System.out.println("Number of theme: ");
        Scanner in = new Scanner(System.in);
        int choice = 0;
        while (choice < 1 || choice > 5) {
            choice = in.nextInt();
            if (choice >= 1 && choice <= 5) {
                break;
            }
            System.out.println("Invalid number");
        }
        return themes[choice - 1];
    }
}
