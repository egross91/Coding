import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();

        in.nextLine();
        String line = in.nextLine();

        boolean[] letters = new boolean[26];
        Arrays.fill(letters, false);

        for (int i = 0; i < line.length(); ++i) {
            int ii = (int)(Character.toLowerCase(line.charAt(i)) - 'a');
            letters[ii] = true;
        }

        for (int i = 0; i < 26; ++i) {
            if (letters[i] == false) {
                System.out.println("NO");
                System.exit(0);
            }
        }

        System.out.println("YES");
    }
}
