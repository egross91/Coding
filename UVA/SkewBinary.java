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
				line = in.readLine().trim();
				if (line == null || line.equals("0") || line.equals("")) break;

				answers.append(solve(line) + "\n");
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	private static int solve(String input)
	{
		int ret = 0;

		int k = 1;
		for (int i = input.length()-1; 0 <= i; --i)
		{
			int mult = (int)(input.charAt(i) - '0');

			// System.out.println("MULT: " + mult + "\nPOW: " + (Math.pow(2, k) - 1));

			ret += (mult * (Math.pow(2, k++) - 1));
		}

		return ret;
	}
}
