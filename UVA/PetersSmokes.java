import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		try
		{
			String line;
			while (true)
			{
				line = in.readLine();
				if (line == null || line.equals("")) break;

				StringTokenizer tokens = new StringTokenizer(line);
				int n = Integer.parseInt(tokens.nextToken());
				int k = Integer.parseInt(tokens.nextToken());

				answers.append(solve(n, k) + "\n");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}

		System.out.print(answers);
	}

	private static int solve(int n, int k)
	{
		int total = 0;
		int leftoverButts = 0;
		int smokables = n;
		int prev = n;

		while (smokables != 0)
		{
			total += smokables;
			smokables = (smokables + leftoverButts) / k;
			leftoverButts = (leftoverButts + prev) % k;
			prev = smokables;
		}

		return total;
	}
}
