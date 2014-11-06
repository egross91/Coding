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

				answers.append(solve(line.split(" ")) + "\n");
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	private static String solve(String[] words)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < words.length; ++i)
		{
			builder.append(reverse(words[i]));
			builder.append(" ");
		}

		return builder.toString().trim();
	}

	private static String reverse(String word)
	{
		char[] letters = word.toCharArray();

		int high = letters.length - 1;
		int low = 0;
		while (low <= high)
		{
			char temp = letters[high];
			letters[high--] = letters[low];
			letters[low++] = temp;
		}

		return new String(letters);
	}
}
