import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    private static final String pi = "31415926535897932384626433833";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();

        int T = Integer.parseInt(in.readLine());
        for (int t = 0; t < T; ++t) {
            answers.append(solve(in.readLine()));
        }

        System.out.print(answers);
    }

    private static final String solve(String line) {
        String[] words = line.replaceAll("[^A-Za-z\\s]", "").replaceAll("\\s+", " ").split(" ");
        int i = 0;
        boolean isPi = true;

        for (String word : words) {
            if (word.length() != (int)pi.charAt(i++)-'0') {
                isPi = false;
                break;
            }
        }

        return (isPi) ? "It\'s a pi song.\n" : "It\'s not a pi song.\n";
    }
}
