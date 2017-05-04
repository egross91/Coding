import java.io.*;
import java.util.*;
// AC
class Main {
    private static int[][] deltas = {{ 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 }}; // Right, down, left, right.
    private static int ROWS;
    private static int COLS;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        while (true) {
            String[] lineData = in.readLine().replaceAll("\\s+", " ").split(" ");
            ROWS = Integer.parseInt(lineData[0]);
            COLS = Integer.parseInt(lineData[1]);

            if (ROWS == 0 && COLS == 0) break;

            boolean[][] graph = new boolean[ROWS][];
            for (int i = 0; i < ROWS; ++i) { graph[i] = new boolean[COLS]; }

            int bombRows = Integer.parseInt(in.readLine());
            for (int i = 0; i < bombRows; ++i) {
                lineData = null;
                lineData = in.readLine().replaceAll("\\s+", " ").split(" ");

                int row = Integer.parseInt(lineData[0]);
                int numBombCols = Integer.parseInt(lineData[1]); // Really don't need this, but whatevs.

                for (int bc = 0; bc < numBombCols; ++bc) {
                    int col = Integer.parseInt(lineData[2+bc]);

                    graph[row][col] = true; // bomb.
                }
            }

            lineData = null;
            lineData = in.readLine().replaceAll("\\s+", " ").split(" ");
            Coordinate start = new Coordinate(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]));

            lineData = null;
            lineData = in.readLine().replaceAll("\\s+", " ").split(" ");
            Coordinate finish = new Coordinate(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1]));

            // System.out.printf("%b%n", graph[finish.row][finish.col]);

            out.append(String.format("%d%n", solve(graph, start, finish)));
        }

        System.out.print(out);
	}

    private static int solve(boolean[][] graph, Coordinate start, Coordinate finish) {
        Set<String> visited = new HashSet<String>();
        Queue<State> Q = new LinkedList<State>();
        Q.offer(new State(start, 0));

        while (!Q.isEmpty()) {
            State current = Q.poll();
            graph[current.location.row][current.location.col] = true; // Mark as bomb because we're done.

            //System.out.println(current);

            if (current.location.row == finish.row && current.location.col == finish.col) {
                return (current.steps);
            }

            for (int i = 0; i < deltas.length; ++i) {
                int deltaRow = current.location.row + deltas[i][0];
                int deltaCol = current.location.col + deltas[i][1];

                if (deltaRow == finish.row && deltaCol == finish.col) {
                    return (current.steps + 1);
                } else if (isWithinBounds(deltaRow, deltaCol) && !graph[deltaRow][deltaCol]) { // && visited.add(new State(new Coordinate(deltaRow, deltaCol), current.steps + 1).toString())) {
                    graph[deltaRow][deltaCol] = true;
                    Q.offer(new State(new Coordinate(deltaRow, deltaCol), current.steps + 1));
                }
            }
        }

        return -1; // Should _never_ reach this state.
    }

    private static boolean isWithinBounds(int row, int col) {
        return ((row >= 0 && row < ROWS) && (col >= 0 && col < COLS));
    }

    private static class Coordinate {
        public final int row;
        public final int col;

        public Coordinate(int r, int c) {
            this.row = r;
            this.col = c;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.row, this.col);
        }
    }

    private static class State {
        public final Coordinate location;
        public final int steps;

        public State(Coordinate c, int s) {
            this.location = c;
            this.steps = s;
        }

        public static Comparator<State> comparator() {
            return new Comparator<State>() {
                @Override
                public int compare(State s1, State s2) {
                    return s1.steps - s2.steps;
                }
            };
        }

        @Override
        public String toString() {
            return String.format("%s[%d]", this.location, this.steps);
        }
    }
}