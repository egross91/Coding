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

				String[] tokens = line.trim().split("\\s+");

				long v = Long.parseLong(tokens[0]);
				long t = Long.parseLong(tokens[1]);

				answers.append(String.format("%d%n", (v * (2 * t))));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}
}