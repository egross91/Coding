import java.util.*;

class Main {
	private static Scanner in = new Scanner(System.in);
	private static int M;
	private static int N;
	private static String[] graph;
	
	private static int[] dy = { -1,-1,-1,0,0,1,1,1 };
	private static int[] dx = { -1,0,1,-1,1,-1,0,1 };
	
	public static void main(String[] args) {
		String input = in.nextLine();
		String[] data = input.split(" ");
		M = Integer.parseInt(data[0]);
		N = Integer.parseInt(data[1]);
		
		while (M != 0 && N != 0) {
			graph = buildGraph();
			int[][] visited = new int[M][N];
			
			int subGraphs = findSubGraphs(visited);
			
			System.out.printf("%d\n", subGraphs);
			
			input = in.nextLine();
			data = input.split(" ");
			M = Integer.parseInt(data[0]);
			N = Integer.parseInt(data[1]);
			graph = null; // Memory leak fix.
			visited = null;
		}
	}
	
	/** BUILD GRAPH **/
	public static String[] buildGraph() {
		String[] temp = new String[M];
		for (int r = 0; r < M; ++r) 
			temp[r] = in.nextLine();
		
		return temp;
	}
	
	/** FIND THE NUMBER OF SUBGRAPHS **/
	public static int findSubGraphs(int[][] visited) {
		int count = 0;
		for (int r = 0; r < M; ++r) {
			for (int c = 0; c < graph[r].length(); ++c) {
				if (graph[r].charAt(c) == '@' && visited[r][c] == 0) {
					DFS(r,c,visited);
					++count;
				}
			}	
		}
		
		return count;
	}
	
	/** ITERATE THROUGH OUR GRAPH **/
	public static void DFS(int r, int c, int[][] visited) {
		visited[r][c] = 1;
		for (int i = 0; i < 8; ++i) {
			int newR = r+dy[i];
			int newC = c+dx[i];
			if ((newR > -1 && newR < M) && (newC > -1 && newC < N) // Boundaries of graph
				&& visited[newR][newC] == 0 && graph[newR].charAt(newC) == '@') {
				DFS(newR,newC,visited);
			}
		}
		
		visited[r][c] = 2;
	}
}
