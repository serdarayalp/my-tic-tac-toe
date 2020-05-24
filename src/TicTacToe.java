import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Schreiben Sie ein Programm, mit dem man das Spiel Tic Tac Toe spielen kann. Verwenden Sie
 * hierzu die drei anderen Programme, die sie bereits erstellt haben, indem Sie die Teile
 * zusammenbauen und um die notwendigen Befehle ergänzen. Vergessen Sie nicht, dass das Spiel auch
 * enden muss, wenn alle Felder besetzt sind und kein Zug mehr möglich ist. In diesem Fall endet
 * das Spiel unentschieden.
 */
public class TicTacToe {

    private static final int PLAYER_1 = 1;
    private static final int PLAYER_2 = 2;

    private static final String PLAYER_1_TOKEN = "X";
    private static final String PLAYER_2_TOKEN = "O";

    private static int[][] field = new int[3][3];

    public static void main(String[] args) throws Exception {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        int curPlayer = PLAYER_1;

        System.out.println("Nächster Spieler: Spieler " + (curPlayer == TicTacToe.PLAYER_1 ? "1" : "2") + " ("
                + (curPlayer == TicTacToe.PLAYER_1 ? PLAYER_1_TOKEN : PLAYER_2_TOKEN) + ")");

        System.out.print("Ihr Zug (Codierung: Wie Telefon-Tastatur. 0 für Ende): ");

        String line = in.readLine();
        int rawMove = Integer.parseInt(line);

        int moveCount = 0;
        int win = 0;
        while (rawMove > 0 && rawMove < 10 && win == 0) {

            int row = 2 - (rawMove - 1) / 3;
            int col = (rawMove - 1) % 3;

            if (field[row][col] == 0) {

                field[row][col] = curPlayer;

                moveCount++;

                System.out.println("+---+---+---+");

                for (int i = 0; i < 3; i++) {

                    System.out.print("| " + (field[i][0] == PLAYER_1 ? PLAYER_1_TOKEN : (field[i][0] == PLAYER_2 ? PLAYER_2_TOKEN : " ")) + " | ");
                    System.out.print((field[i][1] == PLAYER_1 ? PLAYER_1_TOKEN : (field[i][1] == PLAYER_2 ? PLAYER_2_TOKEN : " ")) + " | ");
                    System.out.println((field[i][2] == PLAYER_1 ? PLAYER_1_TOKEN : (field[i][2] == PLAYER_2 ? PLAYER_2_TOKEN : " ")) + " |");

                    System.out.println("+---+---+---+");
                }

                for (int i = 0; win == 0 && i < 3; i++) {
                    int count = field[i][0] != 0 ? 1 : 0;
                    for (int j = 1; win == 0 && j < 3; j++) {
                        if (field[i][j] == field[i][j - 1]) {
                            count++;
                        } else {
                            count = 0;
                        }
                        if (count == 3) {
                            win = field[i][j];
                        }
                    }
                }


                for (int j = 0; win == 0 && j < 3; j++) {
                    int count = field[0][j] != 0 ? 1 : 0;
                    for (int i = 1; win == 0 && i < 3; i++) {
                        if (field[i][j] == field[i - 1][j]) {
                            count++;
                        } else {
                            count = 0;
                        }
                        if (count == 3) {
                            win = field[i][j];
                        }
                    }
                }

                // Links oben - rechts unten
                if (field[0][0] == field[1][1] && field[1][1] == field[2][2]) {
                    win = field[1][1];
                }

                // Rechts oben - links unten
                if (field[0][2] == field[1][1] && field[1][1] == field[2][0]) {
                    win = field[1][1];
                }
                if (win == 0 && moveCount == 9) {
                    win = -1; // Unentschieden, Spielfeld voll
                }
                if (win == 0) {
                    // Nächster Zug
                    curPlayer = curPlayer == PLAYER_1 ? PLAYER_2 : PLAYER_1;
                    System.out.println(
                            "Nächster Spieler: Spieler " + (curPlayer == PLAYER_1 ? "1" : "2") + " ("
                                    + (curPlayer == PLAYER_1 ? PLAYER_1_TOKEN : PLAYER_2_TOKEN) + ")");
                }
            } else {
                System.out.println(
                        "Ungültiger Zug: Feld bereits besetzt! Bitte nochmals eingeben.");
            }
            if (win == 0) {
                System.out.print("Ihr Zug (Codierung: Wie Telefon-Tastatur; 0 für Ende): ");
                line = in.readLine();
                rawMove = Integer.parseInt(line);
            }
        }


        switch (win) {
            case PLAYER_1:
                System.out.println("Spieler 1 gewinnt!");
                break;
            case PLAYER_2:
                System.out.println("Spieler 2 gewinnt!");
                break;
            default:
                System.out.println("Unentschieden!");
                break;
        }
    }
}