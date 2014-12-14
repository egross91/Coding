import java.util.*;
import java.io.*;
import java.math.*;

class Main
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader in = new BufferedReader(new FileReader("ID13.in"));

		String line;
		BigInteger ans = BigInteger.valueOf(0);
		while ((line = in.readLine()) != null) {
			BigInteger value = new BigInteger(line);

			ans = ans.add(value);
		}

		String value = ans.toString();
		int length = value.length();
		System.out.println(value.substring(0, 10));
	}
}
