import java.util.*;
import java.util.regex.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		Pattern pattern = Pattern.compile("[a-zA-Z]");
		try 
		{
			while (true)
			{
				String line = in.readLine();
				if (line == null || line.equals("")) break;

				line = line.replaceAll("[^A-Za-z]", " ");
				StringTokenizer tokens = new StringTokenizer(line);

				int count = 0;
				while (tokens.hasMoreTokens())
				{
					String token = tokens.nextToken();
					if (pattern.matcher(token).find())
						++count;
				}

				answers.append(String.format("%d%n", count));
			}
		}
		catch (Exception exit)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}
}