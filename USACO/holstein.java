/*
ID: eric.bg1
LANG: JAVA
TASK: holstein
*/

import java.util.*;
import java.io.*;
import java.math.*;

class holstein {
    private static int N, G;
    private static int[] targets;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("holstein.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

        N = Integer.parseInt(in.readLine());

        try {
            out.println(solve(in));
            out.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String solve(BufferedReader in) throws Exception {
        targets = new int[N];
        String[] data = in.readLine().replaceAll("\\s+", " ").split(" ");
        for (int i = 0; i < N; ++i) {
            targets[i] = Integer.parseInt(data[i]);
        }

        G = Integer.parseInt(in.readLine());
        int[][] possibilities = new int[G][N];
        for (int i = 0; i < G; ++i) {
            data = null; data = in.readLine().replaceAll("\\s+", " ").split(" ");
            for (int j = 0; j < N; ++j) {
                possibilities[i][j] = Integer.parseInt(data[j]);
            }
        }

        return format(bfs(possibilities));
    }

    private static ArrayList<State> bfs(int[][] possibilities) {
        ArrayList<State> candidates = new ArrayList<State>();

        for (int check = (1<<G)-1; check > 0; --check) {
            int[] vits = new int[N];
            int used = 0;
            for (int i = 0; (1<<i) <= check; ++i) {
                if ((check & (1<<i)) != 0) {
                    used |= (1<<i);
                    for (int j = 0; j < N; ++j) {
                        vits[j] += possibilities[i][j];
                    }
                }
            }

            State candidate = new State(vits, used);
            if (ok(candidate)) {
                candidates.add(candidate);
            }
        }

        return candidates;
    }

    private static String format(ArrayList<State> candidates) {
        State ans = getBestCandidate(candidates);
        StringBuilder builder = new StringBuilder();
        builder.append(getBitCount(ans.vitamins));

        for (int i = 0; (1<<i) <= (1<<G)-1; ++i) {
            if ((ans.vitamins & (1<<i)) != 0) {
                builder.append(" " + (i+1));
            }
        }

        return builder.toString();
    }

    private static int getBitCount(int val) {
        int count = 0;
        for (int i = 0; (1<<i) <= (1<<G)-1; ++i) {
            if ((val & (1<<i)) != 0) {
                ++count;
            }
        }

        return count;
    }

    private static State getBestCandidate(ArrayList<State> candidates) {
        int[] dummy = new int[N];
        Arrays.fill(dummy, Integer.MAX_VALUE);
        State best = new State(dummy, (1<<G)-1);
        for (State candidate : candidates) {
            if (getBitCount(candidate.vitamins) <= getBitCount(best.vitamins)) {
                best = null;
                best = candidate;
            }
        }

        return best;
    }

    private static boolean ok(State state) {
        final int[] vals = state.values;
        for (int i = 0; i < N; ++i) {
            if (vals[i] < targets[i]) {
                return false;
            }
        }

        return true;
    }

    private static class State {
        public final int[] values;
        public final int vitamins;

        public State(int[] c, int vits) {
            this.values = c;
            this.vitamins = vits;
        }
    }
}
