import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args)
	{
		System.out.print(String.format("The 1500'th ugly number is %d.%n", solve()));
	}

	public static long solve()
	{
		int count = 1;
		long[] uglies = new long[1505];

		uglies[0] = uglies[1] = 1;
		int x, y, z;
		x = y = z = 1;
		while (count < 1500)
		{
			long a = 2 * uglies[x];
			long b = 3 * uglies[y];
			long c = 5 * uglies[z];

			long temp = Math.min(a, Math.min(b, c));
			if (temp != uglies[count])
			{
				++count;
				uglies[count] = temp;
			}

			if (uglies[count] == a)
				++x;
			else if (uglies[count] == b)
				++y;
			else
				++z;
		}

		return uglies[1500];
	}íE