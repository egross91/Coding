import java.util.*;
import java.io.*;

class Main {
	//private static final Scanner in = new Scanner(System.in);
	private static final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	/** Dimensions of graph **/
	private static int M;
	private static int N;
	
	private static String[] graph;
	private static int[][] result;
	
	/** Helps with searching adjacent nodes **/
	private static int[] dr = { -1,1,0,0 };
	private static int[] dc = { 0,0,-1,1 };
	
	public static void main(String[] args) throws Exception {
		int tests = Integer.parseInt(in.readLine());
		
		for (int t = 0; t < tests; ++t) {
			String[] data = in.readLine().split(" ");
			M = Integer.parseInt(data[0]);
			N = Integer.parseInt(data[1]); 
			// in.nextLine(); // read dangling \n character
		
			graph = buildGraph();
		
			fillResult();
			printResult();
			if (t < tests -1)
				System.out.println();
			else
				return;
			in.readLine();
			graph = null;
			result = null;
		}
	}
	
	/** BUILD THE GRAPH WE WILL LATER SEARCH **/
	public static String[] buildGraph() {
		String[] temp = new String[M];
		
		for (int r = 0; r < M; ++r) {
			try {
				temp[r] = in.readLine();
			} catch (IOException e) {
				
			}
		}
		return temp;
	}
	
	/** BFS SEARCH OF OUR DISTANCES NEEDED **/
	private static void BFS(Queue<Pair> Q, int[][] visited) {
		/** SEARCH THROUGH OUR ONES THAT WE FOUND INITIALLY **/
		while (!Q.isEmpty()) {
			Pair cur = Q.poll();
			
			for (int i = 0; i < 4; ++i) {
				int newR = cur.row + dr[i];
				int newC = cur.col + dc[i];
				int newDist = cur.dist+1;
				if ((newR > -1 && newR < M) && (newC > -1 && newC < N) // Graph boundaries
					&& graph[newR].charAt(newC) == '0' && (result[newR][newC] == 0)) { // Haven't been visisted
						result[newR][newC] = newDist;
						Q.offer(new Pair(newR,newC,newDist));
				}
			}
		}
	}
	
	/** BEGIN OUR SEARCH, BUT ONLY LOOK FOR THE ONES **/
	public static void fillResult() {
		result = new int[M][N];
		
		int[][] visited = new int[M][N];
		Queue<Pair> Q = new LinkedList<Pair>();
		
		for (int r = 0; r < M; ++r) {
			for (int c = 0; c < N; ++c) {
				// Found a 1
				if (graph[r].charAt(c) == '1') {
					Q.offer(new Pair(r,c,0));
					result[r][c] = 0;
				}
			}
		}
		
		BFS(Q,visited);
	}
	
	public static void printResult() {
		StringBuilder sb = new StringBuilder();
		for (int r = 0; r < M; ++r) {
			for (int c = 0; c < N; ++c) {
				if (c < N-1)
					sb.append(result[r][c] + " ");
				else
					sb.append(result[r][c] + "\n");
			}
		}
		System.out.print(sb);
	}
}

class Pair {
	public final int row;
	public final int col;
	public final int dist;
	Pair() {
		this.row = 0;
		this.col = 0;
		this.dist = 0;
	}
	Pair(int x, int y, int d) {
		this.row = x;
		this.col = y;
		this.dist = d;
	}
}
