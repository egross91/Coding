/*
ID: eric.bg1
LANG: JAVA
TASK: combo
*/
import java.util.*;
import java.io.*;

class combo
{
	private static int N;

	public static void main(String[] args) throws IOException
	{
		BufferedReader in = new BufferedReader(new FileReader("combo.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));

		N = Integer.parseInt(in.readLine().trim());

		String[] combo = in.readLine().split(" ");
		Combo farmer = new Combo(Integer.parseInt(combo[0]), Integer.parseInt(combo[1]), Integer.parseInt(combo[2]));

		combo = in.readLine().split(" ");
		Combo master = new Combo(Integer.parseInt(combo[0]), Integer.parseInt(combo[1]), Integer.parseInt(combo[2]));

		out.println(solve(farmer, master));
		out.close();
		System.exit(0);
	}

	private static int solve(Combo farmer, Combo master)
	{
		int count = 0;

		for (int x = 1; x <= N; ++x)
		{
			for (int y = 1; y <= N; ++y)
			{
				for (int z = 1; z <= N; ++z)
				{
					Combo comp = new Combo(x, y, z);
					if (check(farmer, comp) || check(master, comp))// && notSame(comp))
					{
						++count;
					}
				}
			}
		}

		return count;
	}

	private static boolean check(Combo combo, Combo comp)
	{
		boolean one = withinTwo(combo.x, comp.x); // || withinTwo(combo.x % N, comp.x % N);
		boolean two = withinTwo(combo.y, comp.y); // || withinTwo(combo.y % N, comp.y % N);
		boolean three = withinTwo(combo.z, comp.z); // || withinTwo(combo.z % N, comp.z % N);

		return one && two && three;
	}

	private static boolean withinTwo(int one, int two)
	{
		int diff = Math.abs(one-two);

		return (diff <= 2 || diff >= (N-2)) ? true : false;
	}
}

class Combo
{
	public int x;
	public int y;
	public int z;

	public Combo()
	{
		this.x = this.y = this.z = 0;
	}

	public Combo(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public String toString()
	{
		return (this.x + " " + this.y + " " + this.z);
	}
}
