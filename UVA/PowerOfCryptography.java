import java.util.*;
import java.io.*;
import java.math.*;

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

				int n = Integer.parseInt(line.trim().replaceAll("\\s+", ""));

				line = in.readLine();
				BigInteger p = new BigInteger(line.trim().replaceAll("\\s+", ""));

				answers.append(String.format("%s%n", solve(n, p)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.print(answers);
	}

	public static String solve(int n, BigInteger p)
	{
		int high = 1000000000;
		int low = 1;
		int mid = (high + low) / 2;
		
		int result = -1;
		BigInteger k = BigInteger.valueOf(mid);
		BigInteger kn = new BigInteger(k.toString());
		while (result != 0)
		{
			kn = k.pow(n);
			result = kn.compareTo(p);

			if (result == -1)
				low = mid;
			else if (result == 1)
				high = mid;
			else
				break;

			mid = (high + low) / 2;
			k = BigInteger.valueOf(mid);
		}

		return k.toString();
	}
}