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
				if (line == null || line.equals("")) break;

				int t = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

				for (int i = 1; i <= t; ++i)
					answers.append(String.format("Case %d: %d%n", i, solve(in)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static int solve(BufferedReader in) throws IOException
	{
		String line = in.readLine();
		int a = Integer.parseInt(line.trim().replaceAll("\\s+", ""));
		line = in.readLine();
		int b = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

		return (a < b) ? oddSum(a, b) : oddSum(b, a);
	}

	public static int oddSum(int low, int high)
	{
		int sum = 0;

		for (; low <= high; ++low)
		{
			if ((low & 0x1) == 1)
				sum += low;
		}

		return sum;
	}
}