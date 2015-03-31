/*
ID: eric.bg1
LANG: JAVA
TASK: sort3
*/

import java.util.*;
import java.io.*;
import java.math.*;

class sort3 {
    private static int N, lo, med, hi;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));

        N = Integer.parseInt(in.readLine());

        try {
            out.println(solve(in));
            out.close();
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int solve(BufferedReader in) throws IOException {
        int[] ranks = new int[N];
        int[] count = new int[4];
        for (int i = 0; i < N; ++i) {
            ranks[i] = Integer.parseInt(in.readLine());
            count[ranks[i]]++;
        }

        lo = count[1];
        med = lo + count[2];
        hi = med + count[3];

        final int[] target = getGoalRanks();
        int swaps = 0;
        boolean done = false;
        while (!done) {
            done = true;
            for (int i = 0; i < N; ++i) {
                // Out of place.
                if (ranks[i] != target[i]) {
                    done = false;

                    int ind = -1;
                    for (int j = i+1; j < N; ++j) {
                        if (ranks[j] == target[i] && // The element we're looking at goes here.
                            ranks[i] == target[j]) {
                            ind = j;
                            break;
                        }
                        else if (ranks[j] == target[i]) {
                            ind = j;
                        }
                    }

                    if (ind != -1) {
                        int temp = ranks[ind];
                        ranks[ind] = ranks[i];
                        ranks[i] = temp;

                        ++swaps;
                    }
                }
            }
        }

        return swaps;
    }

    private static int[] getGoalRanks() {
        int[] ret = new int[N];

        int i = 0;
        while (i < lo) {
            ret[i++] = 1;
        }
        while (i < med) {
            ret[i++] = 2;
        }
        while (i < hi) {
            ret[i++] = 3;
        }

        return ret;
    }
}
