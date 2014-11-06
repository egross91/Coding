import java.util.*;
import java.io.*;

class Main
{
	private static int count = 1;

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

				line = fixString(line);
				
				answers.append(String.format("%s%n", line));				
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static String fixString(String line)
	{
		StringBuilder builder = new StringBuilder();
		int prev = 0;
		int curr = -1;

		boolean flag = false;
		while ((curr = line.indexOf("\"", curr+1)) != -1)
		{	
			flag = true;
			builder.append(line.substring(prev, curr));
			if ((count & 0x1) == 1)
			{
				builder.append("``");
			}
			else 
			{
				builder.append("''");
			}

			++count;
			prev = curr+1;
		}

		if (!flag)
			builder.append(line);
		else if ((prev) < line.length())
			builder.append(line.substring(prev));

		return builder.toString();
	}
}