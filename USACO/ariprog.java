/*
ID: eric.bg1
LANG: JAVA
TASK: ariprog
*/
import java.util.*;
import java.io.*;
import java.math.*;

@SuppressWarnings("unchecked")
class ariprog {
    private static int N;
    private static int M;
    private static int max = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("ariprog.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
        StringBuilder builder = new StringBuilder();

        N = Integer.parseInt(in.readLine());
        M = Integer.parseInt(in.readLine());

        int[] bisquares = getBisquares();

        ArrayList<Pair> answers = getAnswers(bisquares);
        Collections.sort(answers);

        if (answers.size() == 0) {
            out.println("NONE");
        }
        else {
            for (int i = 0; i < answers.size(); ++i) {
                Pair current = answers.get(i);
                out.println(current.a + " " + current.b);
            }
        }

        out.close();
        System.exit(0);
    }

    private static int[] getBisquares() {
        int[] ret = new int[125005];
        Arrays.fill(ret, 0);

        for (int p = 0; p <= M; ++p) {
            for (int q = 0; q <= M; ++q) {
                int square = (p*p) + (q*q);
                ret[square] = 1;
                max = Math.max(square, max);
            }
        }

        return ret;
    }

    private static ArrayList<Pair> getAnswers(final int[] bisquares) {
        ArrayList<Pair> ret = new ArrayList<Pair>();

        for (int i = 0; i <= max; ++i) {
            if (bisquares[i] != 1) continue;

            for (int j = 1; j <= 5000; ++j) {
                int sum = i;
                boolean ok = true;

                for (int m = 1; m < N; ++m) {
                    sum += j;
                    if (sum > 125000 || bisquares[sum] != 1) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    ret.add(new Pair(i, j));
                }
            }
        }

        return ret;
    }


    private static class Pair implements Comparable<Pair> {
        public final int a;
        public final int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.b < o.b) {
                return -1;
            }
            else if (o.b < this.b) {
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
