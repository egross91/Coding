/*
ID: eric.bg1
LANG: JAVA
TASK: numtri
*/
import java.util.*;
import java.io.*;
import java.math.*;

class numtri {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("numtri.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));

        N = Integer.parseInt(in.readLine());
        int[][] triangle = new int[N][];
        int[][] count = new int[N][];
        for (int i = 1; i <= N; ++i) {
            triangle[i-1] = new int[i];
            count[i-1] = new int[i];
            String[] data = in.readLine().split(" ");

            for (int j = 0; j < i; ++j) {
                int val = Integer.parseInt(data[j]);
                triangle[i-1][j] = val;
                count[i-1][j] = val;
            }
        }

        out.println(solve(triangle, count));
        out.close();
        System.exit(0);
    }

    private static long solve(final int[][] tri, int[][] track) {
        for (int r = N-1; r > 0; --r) {
            for (int c = 0; c <= r-1; ++c) {
                track[r-1][c] = Math.max(track[r-1][c], tri[r-1][c] + track[r][c]);
                track[r-1][c] = Math.max(track[r-1][c], tri[r-1][c] + track[r][c+1]);
            }
        }

        // for (int i = 0; i < N; ++i) {
        //     System.out.println(Arrays.toString(track[i]));
        // }

        return track[0][0];
    }
}
