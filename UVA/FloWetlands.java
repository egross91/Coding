import java.util.*;

class Main {
	private static Scanner in = new Scanner(System.in);
	private static String line;
	private static String[] data;
	private static ArrayList<String> graph;
	private static int nodes;
	
	private static int[] dr = { -1,-1,-1,0,0,1,1,1 };
	private static int[] dc = { -1,0,1,-1,1,-1,0,1 };
	
	public static void main(String[] args) {
		int tests = in.nextInt();
		in.nextLine(); // Get rid of dangling \n
		in.nextLine(); // Skip the blank line
		
		for (int i = 0; i < tests; ++i) {
			graph = buildGraph();
			
			printResults();
			if (i < tests-1) // Avoid WA
				System.out.println();			
		}
	}
	
	public static void DFS(int r, int c, int[][]visited) {
		visited[r][c] = 1;
		++nodes;
		for (int i = 0; i < 8; ++i) {
			int newR = r+dr[i];
			int newC = c+dc[i];
			if ((newR > -1 && newR < graph.size()) && (newC > -1 && newC < graph.get(0).length()) 
			 	&& (visited[newR][newC] == 0 && graph.get(newR).charAt(newC) == 'W')) {
				DFS(newR,newC,visited);
			}
		}
		visited[r][c] = 2;
	}
	
	public static ArrayList<String> buildGraph() {
		ArrayList<String> temp = new ArrayList<String>();
		
		while ((line = in.nextLine()) != null && (line.charAt(0) == 'L' || line.charAt(0) == 'W')) 
			temp.add(line);
		
		return temp;
	}
	
	public static void printResults() {
		while (!line.equals("")) {
			data = line.split(" ");
			int x = Integer.parseInt(data[0])-1;
			int y = Integer.parseInt(data[1])-1;
			int[][] visited = new int[graph.size()][graph.get(0).length()];
			nodes = 0;
			
			DFS(x,y,visited);
			
			System.out.printf("%d\n", nodes);
			try {
				line = in.nextLine();
			} catch (NoSuchElementException n) {
				return;
			}
		}
	}
}
