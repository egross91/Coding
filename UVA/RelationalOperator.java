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
			line = in.readLine();
			int n = Integer.parseInt(line);
			while (n-- != 0)
			{
				line = in.readLine();
				if (line == null || line.equals("")) break;

				StringTokenizer tokens = new StringTokenizer(line);

				long left = Long.parseLong(tokens.nextToken());
				long right = Long.parseLong(tokens.nextToken());

				answers.append(String.format("%s%n", check(left, right)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static String check(long left, long right)
	{
		if (left == right)
			return "=";
		else if (left < right)
			return "<";
		return ">";
	}
}