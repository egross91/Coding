import java.util.*;
import java.io.*;

class Main
{
	private static final int MAX_LENGTH = 5;

	public static void main(String[] args)
	{
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
		StringBuilder answers = new StringBuilder();

		answers.append("PERFECTION OUTPUT\n");
		try
		{
			int value;
			while (true)
			{
				value = in.nextInt();
				if (value == 0) break;

				answers.append(solve(value));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}
		answers.append("END OF OUTPUT");

		System.out.println(answers);
	}

	private static String solve(int value)
	{
		StringBuilder builder = new StringBuilder();

		int limit = value / 2;
		int sum = 0;
		for (int i = 1; i <= limit; ++i)
		{
			if ((value % i) == 0)
				sum += i;
		}

		builder.append(getSpaces(value) + value + "  ");
		if (sum == value)
			builder.append("PERFECT\n");
		else if (sum < value)
			builder.append("DEFICIENT\n");
		else
			builder.append("ABUNDANT\n");

		return builder.toString();
	}

	private static String getSpaces(int value)
	{
		StringBuilder builder = new StringBuilder();
		String val = Integer.toString(value);

		for (int i = 0; i < MAX_LENGTH - val.length(); ++i)
			builder.append(" ");

		return builder.toString();
	}
}
