/*
* ID: eric.bg1
* LANG: JAVA
* TASK: milk3
*/
import java.io.*;
import java.util.*;

class Main
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("calfflac.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("calfflac.out")));

		StringBuilder builder = new StringBuilder();

		String temp = in.readLine();
		while (temp != null)
		{
			builder.append(temp + "\n");
			temp = in.readLine();
		}

		String longestPalin = getLongestPalin(builder.toString());
		int length = longestPalin.length();
		String answer = getActual(builder.toString(), longestPalin);

		out.println(length);
		out.println(answer);
		out.close();
		System.exit(0);
	}

	public static String getLongestPalin(String input)
	{
		// Remove all non-alphanumeric character out of the string.
		input = input.replaceAll("[^A-Za-z]+", "");

		String longest = input.substring(0, 1);

		for (int i = 0; i < input.length(); ++i)
		{
			String palin = intermediatePalindrome(input, i, i);
			if (palin.length() > longest.length())
				longest = palin;

			palin = intermediatePalindrome(input, i, i+1);
			if (palin.length() > longest.length())
				longest = palin;
		}

		return longest;
	}

	public static String intermediatePalindrome(String str, int left, int right)
	{
		if (left > right) return null;

		while (left >= 0 && right < str.length()
			   && Character.toLowerCase(str.charAt(left)) == Character.toLowerCase(str.charAt(right)))
		{
			--left;
			++right;
		}

		return str.substring(left + 1, right);
	}

	public static String getActual(String input, String palin)
	{
		StringBuilder builder = new StringBuilder();
		ArrayList<Integer> indices = getIndices(input, palin);

		for (int i = 0 ; i < indices.size(); ++i)
		{
			int index = indices.get(i);

			int ii = 0;
			for (; ii < palin.length(); ++ii)
			{
				builder.append(input.charAt(index));
				if (input.charAt(index) == palin.charAt(ii))
				{
					++index;
					continue;
				}
				else if (input.charAt(index) != palin.charAt(ii) && !Character.isLetter(input.charAt(index)))
				{
					++index;
					--ii;
					continue;
				}

				break;
			}

			if (ii >= palin.length()) return builder.toString();

			builder.setLength(0);
		}

		return builder.toString();
	}
	public static ArrayList<Integer> getIndices(String input, String palin)
	{
		ArrayList<Integer> retArr = new ArrayList<Integer>();

		int i = 0;
		int index = 0;
		index = input.indexOf(palin.charAt(0), index);
		while (index != -1)
		{
			retArr.add(index);
			index = input.indexOf(palin.charAt(0), index + 1);
		}

		return retArr;
	}
}
