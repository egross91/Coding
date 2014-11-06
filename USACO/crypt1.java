/*
ID: eric.bg1
LANG: JAVA
TASK: crypt1
*/
import java.io.*;
import java.util.*;

class crypt1
{
	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("crypt1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));

		int n = Integer.parseInt(in.readLine());
		String[] numbers = in.readLine().split(" ");

		int answer = solve(n, numbers);

		out.println(answer);
		out.close();
		System.exit(0);
	}

	public static int solve(int n, String[] numbers)
	{
		HashSet<Integer> crypts = getCrypts(numbers);
		int answers = 0;

		for (int top = 111;  top < 1000; ++top)
		{
			for (int bottom = 11; bottom < 100; ++bottom)
			{
				int product = (top * bottom);
				if (Integer.toString(product).length() != 4)
					continue;

				int partial1 = getPartial(top, Integer.parseInt(Integer.toString(bottom).substring(1, 2)));
				if (Integer.toString(partial1).length() != 3)
					continue;

				int partial2 = product - partial1;
				if (Integer.toString(partial2).length() != 4 && Integer.toString(partial2).charAt(3) != '0')
					continue;

				if (hasAllCrytps(Integer.toString(top), crypts, -1) && hasAllCrytps(Integer.toString(bottom), crypts, -1) 
					&& hasAllCrytps(Integer.toString(partial1), crypts, -1)
					&& hasAllCrytps(Integer.toString(partial2), crypts, 3) && hasAllCrytps(Integer.toString(product), crypts, -1)) {
					++answers;
				}
			}
		}

		return answers;
	}

	public static HashSet<Integer> getCrypts(String[] numbers)
	{
		HashSet<Integer> ret = new HashSet<Integer>();

		for (int i = 0 ; i < numbers.length; ++i)
			ret.add(Integer.parseInt(numbers[i]));

		return ret;
	}

	public static int getPartial(int top, int right)
	{
		return (top * right);
	}

	public static boolean hasAllCrytps(String number, HashSet<Integer> crypts, int index)
	{
		for (int i = 0; i < number.length(); ++i)
		{
			int num = (int)(number.charAt(i) - '0');
			if (!crypts.contains(num) && i != index) {
				return false;
			}
		}

		return true;
	}
}