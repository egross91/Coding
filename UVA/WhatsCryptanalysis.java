import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
class Main
{
	// private static HashMap<Character, Integer> hash;
	private static MyHash[] hash;

	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		String line;
		hash = getHash();
		try
		{
			int N = Integer.parseInt(in.readLine().trim());
			while (N-- != 0)
			{
				line = in.readLine();
				if (line == null || line.equals("")) continue;

				parse(line.replaceAll("[^A-Za-z]", ""));
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
			System.exit(1);
		}

		Arrays.sort(hash, new MyComp());

		for (int i = 0; i < 26; ++i)
		{
			if (hash[i].value != 0)
				System.out.println(hash[i].leChar + " " + hash[i].value);
		}
	}

	private static MyHash[] getHash()
	{
		MyHash[] ret = new MyHash[26];

		for (int i = 0; i < 26; ++i)
		{
			char c = (char)('A' + i);
			ret[i] = new MyHash(c, 0);
		}

		return ret;
	}

	// private static HashMap<Character, Integer> getHash()
	// {
	// 	HashMap<Character, Integer> ret = new HashMap<Character, Integer>();
	//
	// 	ret.put('A', 0);
	// 	ret.put('B', 0);
	// 	ret.put('C', 0);
	// 	ret.put('D', 0);
	// 	ret.put('E', 0);
	// 	ret.put('F', 0);
	// 	ret.put('G', 0);
	// 	ret.put('H', 0);
	// 	ret.put('I', 0);
	// 	ret.put('J', 0);
	// 	ret.put('K', 0);
	// 	ret.put('L', 0);
	// 	ret.put('M', 0);
	// 	ret.put('N', 0);
	// 	ret.put('O', 0);
	// 	ret.put('P', 0);
	// 	ret.put('Q', 0);
	// 	ret.put('R', 0);
	// 	ret.put('S', 0);
	// 	ret.put('T', 0);
	// 	ret.put('U', 0);
	// 	ret.put('V', 0);
	// 	ret.put('W', 0);
	// 	ret.put('X', 0);
	// 	ret.put('Y', 0);
	// 	ret.put('Z', 0);
	//
	// 	return ret;
	// }

	private static void parse(String line)
	{
		for (int i = 0; i < line.length(); ++i)
		{
			char c = Character.toUpperCase(line.charAt(i));
			int index = (Integer)(c - 'A');
			hash[index].value += 1;
		}
	}
}

class MyHash
{
	public char leChar;
	public int value;

	public MyHash()
	{
		this.leChar = 'A';
		this.value = 0;
	}

	public MyHash(char c, int v)
	{
		this.leChar = c;
		this.value = v;
	}
}

class MyComp implements Comparator
{
	@Override
	public int compare(Object o1, Object o2)
	{
		MyHash h1 = (MyHash)o1;
		MyHash h2 = (MyHash)o2;

		int diff = h2.value - h1.value;

		if (diff == 0)
			return (int)((int)h1.leChar - (int)h2.leChar);

		return diff;
	}
}
