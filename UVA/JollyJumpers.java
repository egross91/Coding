import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		StringBuilder answers = new StringBuilder();

		while (in.hasNext())
		{
			int n = in.nextInt();
			boolean[] visited = new boolean[n];
			boolean jolly = true;

			int curr = in.nextInt();
			int prev;
			for (int i = 0; i < n-1; ++i)
			{
				prev = curr;
				curr = in.nextInt();
				int diff = Math.abs(curr-prev);

				if (diff == 0 || diff >= n || visited[diff])
					jolly = false;
				else
					visited[diff] = true;
			}


			if (jolly)
			{
				answers.append("Jolly\n");
			}
			else
			{
				answers.append("Not jolly\n");
			}
		}

		System.out.print(answers);
	}
}
