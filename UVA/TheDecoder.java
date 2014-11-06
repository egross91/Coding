import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		StringBuilder decoded = new StringBuilder();
		String input = "";
		try
		{
			DataInputStream in = new DataInputStream(System.in);
			DataOutputStream out = new DataOutputStream(System.out);

			int curr;
			while ((curr = in.read()) != -1)
			{
				out.write(Character.isWhitespace(curr) ? curr : curr-7);
			}
		}
		catch (Exception e)
		{
			System.exit(0);
		}

		System.out.print(decoded);
	}
}