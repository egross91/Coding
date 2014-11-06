import java.util.*;
import java.io.*;

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

				StringTokenizer token = new StringTokenizer(line);
				int N = Integer.parseInt(token.nextToken());

				if (N <= 101)
				{
					answers.append(String.format("f91(%d) = 91%n", N));
				}
				else
				{
					answers.append(String.format("f91(%d) = %d%n", N, N-10));
				}
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.printf("%s", answers);
	}
}