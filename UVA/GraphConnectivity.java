import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		Solve s = new Solve();
		try {
			s.solve();
		} catch (IOException e) {
			return;
		}
	}
}

class Solve {
	public void solve() throws Exception {
		// Scanner in = new Scanner(System.in);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int tests = Integer.parseInt(in.readLine());
		
		in.readLine();
		for (int i = 0; i < tests; ++i) {
			String max = in.readLine();
			char maximal = max.charAt(0);
			UnionFind UF = new UnionFind(maximal-'A'+1);
			
			while (true) {
				String data = in.readLine();
				if (data.equals("")) break;
				// UF.add(data.charAt(0)-'A', data.charAt(1)-'A');
				UF.merge((int)data.charAt(0)-'A', (int)data.charAt(1)-'A');
			}
			System.out.printf("%d\n\n", UF.getNumSubGraphs());
		}
	}
}

class UnionFind {
	private int[] parent;
	private int numSubGraphs;
	public UnionFind(int size) {
		this.numSubGraphs = size;
		parent = new int[size];
		Arrays.fill(parent,-1);
		/*for (int i = 0; i < size; ++i)
			parent[i] = -1; */
	}
	public int getNumSubGraphs() {
		return this.numSubGraphs;
	}
	
	/* public void add(int x, int y) {
		parent[x] = y;
	} */
	public int find(int x) { // Path Compression
		if (parent[x] == -1) 
			return x;
		// otherwise
		parent[x] = find(parent[x]);
		return parent[x];
	}
	public void merge(int x, int y) {
		int xRep = find(x);
		int yRep = find(y);
		if (xRep != yRep) {
			parent[xRep] = yRep;
			--numSubGraphs; 
		}
	}
}