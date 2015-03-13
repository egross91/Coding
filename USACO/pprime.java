/*
ID: eric.bg1
LANG: JAVA
TASK: pprime
*/

import java.util.*;
import java.io.*;
import java.math.*;

class pprime {
    private static int a;
    private static int b;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("pprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));

        String[] data = in.readLine().split(" ");
        a = Integer.parseInt(data[0]);
        b = Integer.parseInt(data[1]);

        out.print(solve());
        out.close();
        System.exit(0);
    }

    // AC
    private static String solve() {
        Set<Integer> answers = new HashSet<Integer>();

        for (int d1 = 1; d1 <= 9; d1 += 2) {
            int candidate = d1;
            if (ok(candidate)) {
                answers.add(candidate);
            }
            candidate = d1*10 + d1;
            if (ok(candidate)) {
                answers.add(candidate);
            }
            for (int d2 = 0; d2 <= 9; ++d2) {
                candidate = d1*100 + d2*10 + d1;
                if (ok(candidate)) {
                    answers.add(candidate);
                }
                candidate = d1*1000 + d2*100 + d2*10 + d1;
                if (ok(candidate)) {
                    answers.add(candidate);
                }
                for (int d3 = 0; d3 <= 9; ++d3) {
                    candidate = d1*10000 + d2*1000 + d3*100 + d2*10 + d1;
                    if (ok(candidate)) {
                        answers.add(candidate);
                    }
                    candidate = d1*100000 + d2*10000 + d3*1000 + d3*100 + d2*10 + d1;
                    if (ok(candidate)) {
                        answers.add(candidate);
                    }
                    for (int d4 = 0; d4 <= 9; ++d4) {
                        candidate = (d1*1000000 + d2*100000 + d3*10000 + d4*1000 + d3*100 + d2*10 + d1);
                        if (ok(candidate)) {
                            answers.add(candidate);
                        }
                        candidate = (d1*10000000 + d2*1000000 + d3*100000 + d4*10000 + d4*1000 + d3*100 + d2*10 + d1);
                        if (ok(candidate)) {
                            answers.add(candidate);
                        }
                    }
                }
            }
        }

        return format(answers);
    }

    private static String format(Set<Integer> vals) {
        ArrayList<Integer> ans = new ArrayList<Integer>(vals);
        if (ans.size() == 0) {
            return "NONE\n";
        }

        Collections.sort(ans);
        StringBuilder builder = new StringBuilder();
        for (Integer val : ans) {
            builder.append(val + "\n");
        }

        return builder.toString();
    }

    private static boolean ok(int candidate) {
        boolean prime = true;
        int limit = (int)   Math.sqrt(candidate)+1;
        for (int i = 2; i <= limit; ++i) {
            if (candidate%i == 0) {
                prime = false;
                break;
            }
        }

        return (prime && candidate >= a && candidate <= b);
    }
}
