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

				int tests = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

				for (int i = 0; i < tests; ++i)
				{
					line = in.readLine();

					String[] data = line.trim().replaceAll("\\s+", " ").split("\\s+");

					int a = Integer.parseInt(data[0]);
					int b = Integer.parseInt(data[1]);
					int c = Integer.parseInt(data[2]);

					int ans = 0;

					if ((a < b && b < c) || (c < b && b < a))
						ans = b;
					else if ((b < a && a < c) || (c < a && a < b))
						ans = a;
					else
						ans = c;

					answers.append(String.format("Case %d: %d%n", i+1, ans));
				}
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}
}