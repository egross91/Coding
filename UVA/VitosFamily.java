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
			int n = Integer.parseInt(in.readLine().trim());
			while (n-- != 0)
			{
				line = in.readLine();
				if (line == null || line.equals("")) break;

				String[] data = line.trim().split(" ");
				int r = Integer.parseInt(data[0]);
				int[] arr = format(r, data);

				Arrays.sort(arr);
				answers.append(solve(r, arr) + "\n");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}

		System.out.print(answers.toString());
	}

	private static int[] format(int r, String[] data)
	{
		int[] ret = new int[r];

		for (int i = 1; i < data.length; ++i)
		{
			ret[i-1] = Integer.parseInt(data[i]);
		}

		return ret;
	}

	private static int solve(int r, int[] data)
	{
		int dist = 0;

		int high = r-1;
		int low = 0;
		while (low <= high)
		{
			dist += Math.abs(data[low++] - data[high--]);
		}

		return dist;
	}
}
