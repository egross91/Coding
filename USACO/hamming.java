/*
ID: eric.bg1
LANG: JAVA
TASK: hamming
*/

import java.util.*;
import java.io.*;
import java.math.*;

class hamming {
    private static int N, B, D, target;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("hamming.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));
        String[] data = in.readLine().split(" ");
        N = Integer.parseInt(data[0]);
        B = Integer.parseInt(data[1]);
        D = Integer.parseInt(data[2]);
        target = (1<<B)-1;

        out.println(solve());
        out.close();
        System.exit(0);
    }

    private static String solve() {
        ArrayList<Integer> values = new ArrayList<Integer>();
        values.add(0);

        for (int i = 1; i <= target && values.size() < N; ++i) {
            if (isHamming(i, values)) {
                values.add(i);
            }
        }

        return format(values);
    }

    private static boolean isHamming(int current, ArrayList<Integer> prev) {
        for (Integer val : prev) {
            if (getBitCountDiff(current, val) < D) {
                return false;
            }
        }

        return true;
    }

    private static int getBitCountDiff(int current, int comp) {
        int count = 0;
        for (int i = 0; (1<<i) <= target; ++i) {
            if ((current & (1<<i)) != (comp & (1<<i))) {
                ++count;
            }
        }

        return count;
    }

    private static String format(ArrayList<Integer> values) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.valueOf(values.get(0)));

        for (int i = 1; i < values.size(); ++i) {
            if ((i%10) == 0) {
                builder.append("\n");
                builder.append(String.valueOf(values.get(i)));
            }
            else {
                builder.append(" " + String.valueOf(values.get(i)));
            }
        }

        return builder.toString();
    }
}
