import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("514B.in"));
        StringBuilder builder = new StringBuilder();

        while (in.hasNext()) {
            int n = in.nextInt();
            int mx = in.nextInt();
            int my = in.nextInt();

            List<Pair> coords = new LinkedList<Pair>();
            for (int i = 0; i < n; ++i) {
                int x = in.nextInt();
                int y = in.nextInt();

                coords.add(new Pair(x, y));
            }

            solve(coords, new Pair(mx, my));
        }
    }

    public static void solve(List<Pair> coords, Pair myCoords) {
        int count = 0;

        while (coords.size() > 0) {
            Pair two = coords.get(0);

            for (int i = 1; i < coords.size(); ++i) {
                Pair three = coords.get(i);

                if (((two.x-myCoords.x)*(three.y-myCoords.y)) == ((three.x-myCoords.x)*(two.y-myCoords.y))) {
                    coords.remove(i--);
                }
            }
            coords.remove(0);

            ++count;
        }

        System.out.println(count);
    }

    private static class Pair {
        public final int x;
        public final int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
