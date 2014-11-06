import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		try
		{
			String line = in.readLine();
			while (true)
			{
				if (line == null || line.equals("")) break;

				int tests = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

				for (int i = 0; i < tests; ++i)
				{
					line = in.readLine();
					if (line == null || line.equals("")) break;

					String[] data = line.trim().replaceAll("\\s+", " ").split("\\s+");
					int n = Integer.parseInt(data[0]);

					int[] scores = new int[n];
					for (int j = 0; j < n; ++j)
						scores[j] = Integer.parseInt(data[j+1]);

					answers.append(String.format("%s%%%n", solve(scores, n)));
				}
			}
		}
		catch (Exception e)
		{
			System.out.print(answers);
			System.exit(0);
		}

		System.out.print(answers);
	}

	public static String solve(int[] scores, int n)
	{
		double avg = avg(scores, n);

		double count = 0; 
		for (int i = 0; i < n; ++i)
		{
			if ((double)scores[i] > avg)
				count += 1;
		}

		double ret = (double)((count / n) * 100D);

		return String.format("%.3f", ret);
	}

	private static double avg(int[] scores, int n)
	{
		double sum = sum(scores, n);

		return (double)(sum / n);
	}

	private static double sum(int[] scores, int n)
	{
		double sum = 0;

		for (int i = 0; i < n; ++i)
		{
			sum += scores[i];
		}

		return sum;
	}
}