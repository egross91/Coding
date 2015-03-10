/*
ID: eric.bg1
LANG: JAVA
TASK: milk3
*/

import java.util.*;
import java.io.*;
import java.math.*;

class milk3 {
    private static int maxA;
    private static int maxB;
    private static int maxC;
    private static HashSet<String> visited = new HashSet<String>();

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("milk3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));
        StringBuilder answer = new StringBuilder();

        String[] data = in.readLine().split(" ");
        maxA = Integer.parseInt(data[0]);
        maxB = Integer.parseInt(data[1]);
        maxC = Integer.parseInt(data[2]);

        Set<Integer> answers = solve();
        answer.append(format(answers));

        out.println(answer.toString());
        out.close();
        System.exit(0);
    }

    private static String format(Collection<Integer> numbers) {
        ArrayList<Integer> yee = new ArrayList<Integer>(numbers);
        Collections.sort(yee);

        StringBuilder builder = new StringBuilder();

        builder.append(yee.get(0));
        for (int i = 1; i < yee.size(); ++i) {
            builder.append(" " + String.valueOf(yee.get(i)));
        }

        return builder.toString();
    }

    private static Set<Integer> solve() {
        Set<Integer> answers = new HashSet<Integer>();

        dfs(new State(0, 0, maxC), answers);

        return answers;
    }

    /**
     *  Not proud of this coding, but it worked. AC (y)
     */
    private static void dfs(State current, Set<Integer> answers) {
        if (visited.add(current.toString())) {
            if (current.bA == 0) {
                answers.add(current.bC);
            }

            if (current.bB < maxB) {
                int diff = 0;
                if (current.bA > 0) {
                    diff = (maxB - current.bB);
                    diff = Math.min(current.bA, diff);
                    dfs(new State(current.bA-diff, current.bB+diff, current.bC), answers);
                }
                if (current.bC > 0) {
                    diff = (maxB - current.bB);
                    diff = Math.min(current.bC, diff);
                    dfs(new State(current.bA, current.bB+diff, current.bC-diff), answers);
                }
            }
            if (current.bA < maxA) {
                int diff = 0;
                if (current.bB > 0) {
                    diff = (maxA - current.bA);
                    diff = Math.min(current.bB, diff);
                    dfs(new State(current.bA+diff, current.bB-diff, current.bC), answers);
                }
                if (current.bC > 0) {
                    diff = (maxA - current.bA);
                    diff = Math.min(current.bC, diff);
                    dfs(new State(current.bA+diff, current.bB, current.bC-diff), answers);
                }
            }
            if (current.bC < maxC) {
                int diff = 0;
                if (current.bA > 0) {
                    diff = (maxC - current.bC);
                    diff = Math.min(current.bA, diff);
                    dfs(new State(current.bA-diff, current.bB, current.bC+diff), answers);
                }
                if (current.bB > 0) {
                    diff = (maxC - current.bC);
                    diff = Math.min(current.bB, diff);
                    dfs(new State(current.bA, current.bB-diff, current.bC+diff), answers);
                }
            }
        }
    }

    private static class State {
        public final int bA;
        public final int bB;
        public final int bC;

        public State(int a, int b, int c) {
            this.bA = a;
            this.bB = b;
            this.bC = c;
        }

        @Override
        public String toString() {
            return String.valueOf(bA) + " " + String.valueOf(bB) + " " + String.valueOf(bC);
        }
    }
}
