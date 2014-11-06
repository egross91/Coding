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

				long tests = Long.parseLong(line.trim().replaceAll("\\s+", ""));

				while (tests-- != 0)
				{
					line = in.readLine();
					long number = Long.parseLong(line.trim().replaceAll("\\s+", ""));

					solve(number, answers);
				}
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static void solve(long number, StringBuilder answers)
	{
		long count = 0;
		while (!isPalin(number))
		{
			String revStr = reverse(number);

			++count;
			long rev = Long.parseLong(revStr);

			number += rev;
		}

		answers.append(String.format("%d %d%n", count, number));
	}

	public static boolean isPalin(long number)
	{
		String num = Long.toString(number);

		int high = num.length()-1;
		for (int i = 0; i < num.length() / 2; ++i)
		{
			if (num.charAt(i) != num.charAt(high--))
				return false;
		}

		return true;
	}

	public static String reverse(long number)
	{
		StringBuilder builder = new StringBuilder(Long.toString(number));

		return builder.reverse().toString();
	}
}