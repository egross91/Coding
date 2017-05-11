import java.io.*;
import java.util.*;
// AC
class Main {
    private static Line best = null;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("GettingInLine.in"));
        // PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("GettingInLine.out")));
		StringBuilder out = new StringBuilder();
        int network = 1;
		
        while (true) {
            int numCoords = Integer.parseInt(in.readLine());
            best = null;

            if (numCoords == 0) { break; }

            List<Coordinate> coords = new ArrayList<Coordinate>();

            for (int i = 0; i < numCoords; ++i) {
                String[] lineData = in.readLine().split(" ");

                coords.add(new Coordinate(Integer.parseInt(lineData[0]), Integer.parseInt(lineData[1])));
            }

            out.append("**********************************************************\n");
            out.append(String.format("Network #%d%n", network++));
            out.append(solve(coords));
        }

        // writer.print(out);
        // writer.close();
        System.out.print(out);
	}

    private static String solve(List<Coordinate> coords) {
        for (int i = 0; i < coords.size(); ++i) {
            backtrack(coords, i, new boolean[coords.size()+1], new Line());
        }

        StringBuilder builder = new StringBuilder();
        double distanceSum = 0.0D;

        for (CableRequirement req : best.getRequirements()) {
            builder.append(req.toString());
            builder.append("\n");

            distanceSum += req.getDistance();
        }

        builder.append(String.format("Number of feet of cable required is %.2f.%n", distanceSum));

        return builder.toString();
    }

    private static void backtrack(List<Coordinate> coords, int index, boolean[] used, Line currentLine) {
        if (currentLine.size() == (coords.size()-1)) {
            if (best == null || Double.compare(currentLine.getTotalDistance(), best.getTotalDistance()) == -1) {
                best = new Line(currentLine);
                return;
            }
        }

        used[index] = true;
        Coordinate currentCoord = coords.get(index);

        for (int i = 0; i < coords.size(); ++i) {
            if (used[i]) { continue; }

            CableRequirement req = new CableRequirement(currentCoord, coords.get(i));

            currentLine.add(req);
            backtrack(coords, i, used, new Line(currentLine));
            used[i] = false;

            currentLine.remove(req);
        }

        used[index] = false;
    }

    private static class Line {
        private List<CableRequirement> reqs;
        private double totalDistance;

        public Line() {
            this.reqs = new ArrayList<CableRequirement>();
            this.totalDistance = 0.0D;
        }

        public Line(Line l) {
            this.reqs = new ArrayList<CableRequirement>(l.reqs);
            this.totalDistance = l.totalDistance;
        } 

        public void add(CableRequirement req) {
            this.reqs.add(req);
            this.totalDistance += req.getDistance();
        }

        public void remove(CableRequirement req) {
            this.reqs.remove(req);
            this.totalDistance -= req.getDistance();
        }

        public List<CableRequirement> getRequirements() {
            return this.reqs;
        }

        public int size() {
            return this.reqs.size();
        }

        public double getTotalDistance() {
            return this.totalDistance;
        }
    }

    private static class CableRequirement {
        private Coordinate left, right;
        private double distance;

        public CableRequirement(Coordinate l, Coordinate r) {
            this.left = l;
            this.right = r;

            this.distance = l.distanceTo(r) + 16.0D;
        }

        public Coordinate getLeft() {
            return this.left;
        }

        public Coordinate getRight() {
            return this.right;
        }

        public double getDistance() {
            return this.distance;
        }

        @Override
        public String toString() {
            return String.format("Cable requirement to connect %s to %s is %.2f feet.", this.left, this.right, this.distance);
        }
    }

    private static class Coordinate {
        private final int x, y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double distanceTo(Coordinate c) {
            int deltaX = this.x - c.x;
            int deltaY = this.y - c.y;

            return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", this.x, this.y);
        }
    }
}