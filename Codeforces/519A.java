import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int black = 0;
        int white = 0;
        for (int i = 0; i < 8; ++i) {
            String line = in.nextLine();

            for (int j = 0; j < 8; ++j) {
                Character current = line.charAt(j);
                if ((int)current > 96) {
                    black += weight(current);
                }
                else {
                    white += weight(current);
                }
            }
        }

        if (white == black) {
            System.out.println("Draw");
        }
        else if (white > black) {
            System.out.println("White");
        }
        else {
            System.out.println("Black");
        }
    }

    private static int weight(Character c) {
        Character upper = Character.toUpperCase(c);

        if (upper == 'P') {
            return 1;
        }
        else if (upper == 'R') {
            return 5;
        }
        else if (upper == 'Q') {
            return 9;
        }
        else if (upper == 'B' || upper == 'N') {
            return 3;
        }
        else {
            return 0;
        }
    }
}
