import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
		StringBuilder answers = new StringBuilder();

		try
		{
			int n = in.nextInt();

			while (n-- != 0)
			{
				int s = in.nextInt();
				int d = in.nextInt();

				answers.append(solve(s, d));
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

	private static String solve(int s, int d)
	{
		if (d > s)
			return "impossible\n";

		boolean oddSum = ((s & 1) == 1);
		int high = (s / 2);
		int low = (s / 2);

		if (oddSum)
			++high;

		boolean oddDiff = ((d & 1) == 1);
		int dHigh = (d / 2);
		int dLow = (d / 2);
		if (oddDiff && !oddSum)
			++dHigh;

		// System.out.println(String.format("HIGH: %d %d%nLow: %d %d", high, dHigh, low, dLow));

		if (Math.abs((high + dHigh) - (low - dLow)) == d && (high + dHigh + low - dLow) == s)
			return String.format("%d %d%n", high+dHigh, low-dLow);
		else if (Math.abs((high + dLow) - (low - dHigh)) == d && (high + dLow + low - dHigh) == s)
			return String.format("%d %d%n", high+dLow, low-dHigh);

		if (oddSum)
		{
			--high;
			++low;
		}

		if (Math.abs((high + dHigh) - (low + dLow)) == d && (high + dHigh + low - dLow) == s)
			return String.format("%d %d%n", high+dHigh, low-dLow);
		else if (Math.abs((high + dLow) - (low + dHigh)) == d && (high + dLow + low - dHigh) == s)
			return String.format("%d %d%n", high+dLow, low-dHigh);

		return "impossible\n";
	}
}
