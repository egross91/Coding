import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		String line;
		int set = 1;
		try
		{
			while (true)
			{
				line = in.readLine();
				if (line == null || line.equals("0") || line.equals("")) break;

				int n = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

				line = in.readLine();
				int[] nums = format(line.trim().replaceAll("\\s+", " "), n);

				answers.append(String.format("Set #%d%nThe minimum number of moves is %d.%n%n", set++, solve(nums, n)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static int[] format(String input, int n)
	{
		StringTokenizer tokens = new StringTokenizer(input);

		int[] ret = new int[n];

		for (int i = 0; i < n; ++i)
		{
			ret[i] = Integer.parseInt(tokens.nextToken());
		}

		return ret;
	}

	public static int solve(int[] nums, int n)
	{
		int moves = 0;
		int sum = sum(nums);
		int avg = sum / n;

		for (int i = 0; i < n; ++i)
		{
			if (nums[i] < avg)
				moves += Math.abs(nums[i] - avg);
		}

		return moves;
	}

	private static int sum(int[] nums)
	{
		int sum = 0;

		for (int i = 0; i < nums.length; ++i)
		{
			sum += nums[i];
		}

		return sum;
	}
}