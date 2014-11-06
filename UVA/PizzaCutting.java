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
			int value;
			while (true)
			{
				value = Integer.parseInt(in.readLine().trim());
				if (value < 0) break;

				answers.append(solve(value) + "\n");
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	private static long solve(int val)
	{
		long value = val;

		return 1 + ((value * (value+1)) / 2);
	}
}
