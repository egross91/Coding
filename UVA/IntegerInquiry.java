import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main
{
	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String line;
		BigInteger result = new BigInteger("0");
		try
		{
			while (true)
			{
				line = in.readLine();
				if (line == null || line.equals("0") || line.equals("")) break;

				result = result.add(new BigInteger(line.trim()));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(result);
	}
}
