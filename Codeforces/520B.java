import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] visited = new boolean[14005];
        Arrays.fill(visited, false);

        Queue<State> Q = new LinkedList<State>();
        Q.offer(new State(n, 0));

        while (!Q.isEmpty()) {
            State current = Q.poll();
            visited[current.val] = true;;

            if (current.val == m) {
                System.out.println(current.pushes);
                System.exit(0);
            }

            if (current.val - 1 > -1 && !(visited[current.val-1])) {
                Q.offer(new State(current.val-1, current.pushes+1));
            }
            if ((current.val << 1) <= 14000 && !(visited[current.val << 1])) {
                Q.offer(new State(current.val << 1, current.pushes+1));
            }
        }
    }

    private static class State {
        public final int val;
        public final int pushes;

        public State() {
            this.val = 0;
            this.pushes = 0;
        }

        public State(int v, int p) {
            this.val = v;
            this.pushes = p;
        }
    }
}
