import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		String line;
		try
		{
			while (true)
			{
				line = in.readLine();
				if (line == null || line.equals("0") || line.equals("")) break;

				int n = Integer.parseInt(line.trim());
				int l = Integer.parseInt(in.readLine().trim());

				ArrayList<Integer>[] graph = getGraph(n, l, in);

				answers.append(solve(n, graph));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	private static ArrayList<Integer>[] getGraph(int n, int l, BufferedReader in) throws Exception
	{
		ArrayList<Integer>[] ret = new ArrayList[n];

		for (int i = 0; i < n; ++i)
			ret[i] = new ArrayList<Integer>();

		for (int i = 0; i < l; ++i)
		{
			String[] data = in.readLine().trim().split(" ");

			int node = Integer.parseInt(data[0]);
			int edge = Integer.parseInt(data[1]);

			ret[node].add(edge);
			ret[edge].add(node);
		}

		return ret;
	}

	private static String solve(int n, ArrayList<Integer>[] graph)
	{
		boolean[] visited = new boolean[n];
		boolean[] colors = new boolean[n];
		Queue<Integer> Q = new LinkedList<Integer>();

		Q.offer(0);

		while (!Q.isEmpty())
		{
			int currNode = Q.poll();
			boolean currColor = colors[currNode];

			ArrayList<Integer> edges = graph[currNode];
			for (int i = 0; i < edges.size(); ++i)
			{
				int node = edges.get(i);
				if (!visited[node])
				{
					visited[node] = true;
					Q.offer(node);
				}
				colors[node] = !currColor;
			}
		}

		for (int i = 0; i < n; ++i)
		{
			boolean color = colors[i];
			ArrayList<Integer> edges = graph[i];

			for (int e = 0; e < edges.size(); ++e)
			{
				int edge = edges.get(e);

				if (colors[edge] == color)
					return "NOT BICOLORABLE.\n";
			}
		}

		return "BICOLORABLE.\n";
	}
}
