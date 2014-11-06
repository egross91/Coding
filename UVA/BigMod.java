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
			line = in.readLine().trim();
			while (true)
			{
				long B = Long.parseLong(line.trim());
				long P = Long.parseLong(in.readLine().trim());
				long M = Long.parseLong(in.readLine().trim());

				if (B != 0)
					answers.append(solve(B, P, M) + "\n");
				else
					answers.append("0\n");

				in.readLine();
				line = in.readLine();
				if (line == null || line.equals("")) break;
			}
		}
		catch (Exception e)
		{
			System.out.print(answers);
			System.exit(0);
		}

		System.out.print(answers);
	}

	private static long solve(long B, long P, long M)
	{
		if (B == 0)
			B = M;
		if (P == 0)
			return 1;
		if (P == 1)
			return B;
		if ((P & 0x1) == 0)
			return solve((B * B) % M, P/2, M);
		else
			return (B * solve((B * B) % M, (P-1)/2, M)) % M;
	}
}
