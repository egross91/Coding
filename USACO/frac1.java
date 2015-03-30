/*
ID: eric.bg1
LANG: JAVA
TASK: frac1
*/

import java.util.*;
import java.io.*;
import java.math.*;

@SuppressWarnings("unchecked")
class frac1 {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
        N = Integer.parseInt(in.readLine());

        out.println(solve());
        out.close();
        System.exit(0);
    }

    private static String solve() {
        ArrayList<Triplet> fracs = new ArrayList<Triplet>();
        Set<Double> mags = new HashSet<Double>();

        for (int d = 2; d <= N; ++d) {
            for (int n = 1; n <= d-1; ++n) {
                double magnitude = (double)n / (double)d;
                if (mags.add(magnitude)) {
                    fracs.add(new Triplet(n, d, magnitude));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        Collections.sort(fracs);
        builder.append("0/1\n");
        for (Triplet t : fracs) {
            builder.append(String.valueOf(t.num) + "/" + String.valueOf(t.den) +"\n");
        }
        builder.append("1/1");

        return builder.toString();
    }

    private static class Triplet implements Comparable {
        public final int num;
        public final int den;
        public final double magnitude;

        public Triplet(int n, int d, double m) {
            this.num = n;
            this.den = d;
            this.magnitude = m;
        }

        @Override
        public int compareTo(Object obj) {
            if (obj instanceof Triplet) {
                Triplet t = (Triplet)obj;
                if (this.magnitude < t.magnitude) {
                    return -1;
                }
                else if (this.magnitude > t.magnitude) {
                    return 1;
                }

                return 0;
            }

            return 0;
        }
    }
}
