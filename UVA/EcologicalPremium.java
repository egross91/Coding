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
			int n = Integer.parseInt(in.readLine());
			while (n-- != 0)
			{
				line = in.readLine();

				if (line == null || line.equals("")) break;

				int i = Integer.parseInt(line);
				long cost = 0;
				while (i-- != 0)
				{
					line = in.readLine();
					StringTokenizer tokens = new StringTokenizer(line);

					long size = Long.parseLong(tokens.nextToken());
					long animals = Long.parseLong(tokens.nextToken());
					long friendliness = Long.parseLong(tokens.nextToken());

					cost += (size * friendliness);
				}

				answers.append(String.format("%d%n", cost));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answe­ú>ïˆ¥Þ¹ ê¤