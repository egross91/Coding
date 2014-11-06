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
				if (line == null || line.equals("0 0") || line.equals("")) break;

				StringTokenizer tokens = new StringTokenizer(line);

				long top = Long.parseLong(tokens.nextToken());
				long bottom = Long.parseLong(tokens.nextToken());

				answers.append(String.format("%s%n", countCarry(top, bottom)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static String countCarry(long top, long bottom)
	{
		int carry = 0;

		long topTemp = top;
		long bottomTemp = bottom;
		int toAdd = 0;
		do 
		{
			int topRight = (int)topTemp % 10;
			int bottomRight = (int)bottomTemp % 10;

			// System.out.println(String.format("%d %d %d", topRight, bottomRight, toAdd));
			if ((topRight + bottomRight + toAdd) > 9)
			{
				++carry;
				toAdd = 1;
			}
			else
				toAdd = 0;

			topTemp /= 10;
			bottomTemp /= 10;
		} while (topTemp != 0 || bottomTemp != 0);

		if (carry == 0)
			return "No carry operation.";
		else if (carry == 1)
			return "1 carry operation.";

		return (carry + " carry operations.");
	}
}