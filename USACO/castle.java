/*
ID: eric.bg1
LANG: JAVA
TASK: castle
*/

import java.util.*;
import java.io.*;
import java.math.*;

class castle {
	private static int rows, cols;
	private static final int[] dr = { 1, 0, 0, -1};
	private static final int[] dc = { 0, 1, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("castle.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

		String[] data = in.readLine().split(" ");
		cols = Integer.parseInt(data[0]);
		rows = Integer.parseInt(data[1]);
		try {
			out.println(solve(in));
			out.close();
			System.exit(0);
		} catch(Exception e) {
			// e.printStackTrace();
		}
	}

	private static String solve(BufferedReader in) throws Exception {
		Tile[][] graph = new Tile[rows][cols];
		for (int row = 0; row < rows; ++row) {
			String[] data = in.readLine().split(" ");
			for (int col = 0; col < cols; ++col) {
				int walls = Integer.parseInt(data[col]);
				boolean a = false;
				boolean b = false;
				boolean c = false;
				boolean d = false;

				if (walls >= 8) {
					a = true;
					walls -= 8;
				}
				if (walls >= 4) {
					b = true;
					walls -= 4;
				}
				if (walls >= 2) {
					c = true;
					walls -= 2;
				}
				if (walls == 1) {
					d = true;
				}

				graph[row][col] = new Tile(a, b, c, d);
			}
		}

		return search(graph);
	}

	private static String search(Tile[][] graph) {
		int[][] marks = new int[rows][cols];
		ArrayList<ArrayList<Point>> comps = new ArrayList<ArrayList<Point>>();
		int mark = 0;

		for (int[] a : marks) {
			Arrays.fill(a, -1);
		}

		/* Mark all the components of the castle. */
		for (int r = 0; r < rows; ++r) {
			for (int c = 0; c < cols; ++c) {
				if (marks[r][c] == -1) {
					ArrayList<Point> comp = new ArrayList<Point>();
					fillMarks(r, c, graph, marks, mark++, comp);
					comps.add(comp);
				}
			}
		}

		/* Find the 2 adjacent comps that are the largest. */
		int max = 0;
		Point best = null;
		Point westMost = new Point(0, cols);
		for (int i = 0; i < comps.size(); ++i) {
			ArrayList<Point> current = comps.get(i);
			Set<Integer> adjs = new HashSet<Integer>();
			findAdjs(current, marks, i, adjs);

			for (Integer m : adjs) {
				int total = current.size() + comps.get(m).size();
				Point currentWestMost = getWestMost(current, comps.get(m));

				if (max < total) {
					westMost = currentWestMost;
					max = total;
					best = null;
					best = new Point(i, m);
				}
				else if (max == total && best != null) {
					Point duel = getWestMost(comps.get(m), comps.get(best.c));

					if (marks[duel.r][duel.c] != best.c) {
						best = null;
						best = new Point(i, m);
					}
				}
			}
		}

		if (isUpTheSame(comps, westMost, marks, max)) {
			best = null;
			best = new Point(marks[westMost.r][westMost.c], marks[westMost.r-1][westMost.c]);
		}
		// System.out.println("best: " + best.r + " " + best.c);

		// for (int i = 0; i < marks.length; ++i) {
		// 	System.out.println(Arrays.toString(marks[i]));
		// }

		ArrayList<Point> combine = new ArrayList<Point>(comps.get(best.r));
		combine.addAll(comps.get(best.c));
		Point bottomLeft = new Point(0, cols);
		for (Point p : combine) {
			// System.out.println("p: " + p.r + " " + p.c);
			if ((p.c < bottomLeft.c || (p.c <= bottomLeft.c && p.r >= bottomLeft.r)) && adjToEachOther(p, best, marks)) {
				// System.out.println("west");
				bottomLeft = null;
				bottomLeft = new Point(p.r, p.c);
			}
		}

		// System.out.println("bottomLeft: " + bottomLeft.r + " " + bottomLeft.c);
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(comps.size()) + "\n");
		builder.append(String.valueOf(getMaxSize(comps)) + "\n");
		builder.append(String.valueOf(max) + "\n");
		builder.append(getDestroyWall(marks, bottomLeft, best));

		return builder.toString();
	}

	private static void fillMarks(int r, int c, Tile[][] graph, int[][] marks, int mark
								 , ArrayList<Point> comp) {
		comp.add(new Point(r, c));
		marks[r][c] = mark;
		Tile current = graph[r][c];

		for (int i = 0; i < 4; ++i) {
			int nR = r + dr[i];
			int nC = c + dc[i];

			if (inBounds(nR, nC) && marks[nR][nC] == -1 && canEnter(current, i)) {
				fillMarks(nR, nC, graph, marks, mark, comp);
			}
		}
	}

	private static void findAdjs(ArrayList<Point> comp, int[][] marks, int target
								, Set<Integer> adjs) {
		for (Point p : comp) {
			for (int i = 0; i < 4; ++i) {
				int nR = p.r + dr[i];
				int nC = p.c + dc[i];

				if (inBounds(nR, nC) && marks[nR][nC] != target) {
					adjs.add(marks[nR][nC]);
				}
			}
		}
	}

	private static boolean adjToEachOther(Point current, Point best, int[][] marks) {
		int target = (marks[current.r][current.c] == best.c) ? best.r : best.c;

		for (int i = 0; i < 4; ++i) {
			int nR = current.r + dr[i];
			int nC = current.c + dc[i];

			if (inBounds(nR, nC) && marks[nR][nC] == target) {
				return true;
			}
		}

		return false;
	}

	private static String getDestroyWall(int[][] marks, Point leftMost, Point best) {
		StringBuilder builder = new StringBuilder();
		builder.append(String.valueOf(leftMost.r+1) + " " + String.valueOf(leftMost.c+1) + " ");
		int target = (marks[leftMost.r][leftMost.c] == best.r) ? best.c : best.r;

		if (inBounds(leftMost.r-1, leftMost.c) && marks[leftMost.r-1][leftMost.c] == target) { // Look up
			builder.append("N");
		}
		else if (inBounds(leftMost.r, leftMost.c+1) && marks[leftMost.r][leftMost.c+1] == target) { // Look right
			builder.append("E");
		}

		return builder.toString();
	}

	private static boolean canEnter(Tile t, int dir) {
		switch (dir) {
			case 0:
				return !t.south;
			case 1:
				return !t.east;
			case 2:
				return !t.west;
			case 3:
				return !t.north;
			default:
				throw new RuntimeException("ya goofed");
		}
	}

	private static Point getWestMost(ArrayList<Point> c1, ArrayList<Point> c2) {
		Point westMost = new Point(0, cols);
		for (Point p : c1) {
			if (p.r >= westMost.r && p.c <= westMost.c) {
				westMost = null;
				westMost = new Point(p.r, p.c);
			}
		}
		for (Point p : c2) {
			if (p.r >= westMost.r && p.c <= westMost.c) {
				westMost = null;
				westMost = new Point(p.r, p.c);
			}
		}

		return westMost;
	}

	private static boolean isUpTheSame(ArrayList<ArrayList<Point>> comps, Point westMost
							   , int[][] marks, int max) {
		if (!inBounds(westMost.r-1, westMost.c)
			|| marks[westMost.r-1][westMost.c] == marks[westMost.r][westMost.c]) {
			return false;
		}
		int thisMark = marks[westMost.r][westMost.c];
		int upMark = marks[westMost.r-1][westMost.c];

		ArrayList<Point> adj = comps.get(upMark);
		int total = adj.size() + comps.get(thisMark).size();

		return total >= max;
	}

	private static int getMaxSize(ArrayList<ArrayList<Point>> comps) {
		int max = 0;
		for (ArrayList<Point> comp : comps) {
			max = Math.max(max, comp.size());
		}

		return max;
	}

	private static boolean inBounds(int r, int c) {
		return (r > -1 && r < rows && c > -1 && c < cols);
	}

	private static class Point {
		public final int r;
		public final int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	private static class Tile {
		public final boolean south;
		public final boolean east;
		public final boolean north;
		public final boolean west;

		public Tile(boolean a, boolean b, boolean c, boolean d) {
			this.south = a;
			this.east = b;
			this.north = c;
			this.west = d;
		}
	}
}
