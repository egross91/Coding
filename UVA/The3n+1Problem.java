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

				int i = Integer.parseInt(tokens[0]);
				int j = Integer.parseInt(tokens[1]);

				int answer = (i < j) ? algo(i, j) : algo(j, i);

				answers.append(String.format("%d %d %d%n", i, j, answer));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static int algo(int i, int j)
	{
		int max = -1;

		for (; i <= j; ++i)
		{
			int length = 1;
			int temp = i;
			while (temp != 1)
			{
				if ((temp & 0x1) == 1)
					temp = (3*temp) + 1;
				else
					temp /= 2;

				++length;
			}

			max = Math.max(max, length);
		}

		return max;
	}
}