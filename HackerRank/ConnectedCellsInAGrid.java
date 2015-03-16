import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    private static int N, M, count, longest = 0;
    private static int[] dr = { 1, 0, -1, 0, -1, -1, 1, 1 };
    private static int[] dc = { 0, 1, 0, -1, -1, 1, -1, 1 };

    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        M = in.nextInt();
        N = in.nextInt();
        System.out.println(solve());
    }

    private static int solve() {
        int[][] graph = new int[M][N];

        for (int r = 0; r < M; ++r) {
            for (int c = 0; c < N; ++c) {
                graph[r][c] = in.nextInt();
            }
        }

        return search(graph);
    }

    private static int search(int[][] G) {
        for (int r = 0; r < M; ++r) {
            for (int c = 0; c < N; ++c) {
                if (G[r][c] == 1) {
                    count = 0;
                    dfs(G, r, c);

                    longest = Math.max(longest, count);
                }
            }
        }

        return longest;
    }

    private static void dfs(int[][] G, int r, int c) {
        ++count;
        G[r][c] = 0;

        for (int i = 0; i < 8; ++i) {
            int nR = r + dr[i];
            int nC = c + dc[i];

            if (nR > -1 && nR < M && nC > -1 && nC < N && G[nR][nC] == 1) {
                dfs(G, nR, nC);
            }
        }
    }
}
