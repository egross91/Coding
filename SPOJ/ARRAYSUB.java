import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        StringBuilder builder = new StringBuilder();

        int N = in.nextInt();
        int[] vals = new int[N];
        for (int i = 0; i < N; ++i) {
            vals[i] = in.nextInt();
        }

        Deque<Pair> Q = new LinkedList<Pair>();
        int K = in.nextInt();
        for (int i = 0; i < N; ++i) {
            while (!Q.isEmpty() && Q.getLast().val <= vals[i]) {
                Q.pollLast();
            }
            Q.offerLast(new Pair(i, vals[i]));

            while (Q.getFirst().index <= i-K) {
                Q.pollFirst();
            }

            if (i >= K-1) {
                builder.append(Q.getFirst().val + " ");
            }
        }

        System.out.println(builder);
    }
}

class Pair {
    public final int index;
    public final int val;

    public Pair(int i, int v) {
        this.index = i;
        this.val = v;
    }
}
