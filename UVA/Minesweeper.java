import java.util.*;
import java.io.*;

class Main
{
	private static int[] dx = { -1, 0, 1, -1, 1, -1, 0, 1 };
	private static int[] dy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static int length;
	private static int width;

	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		String line;
		try
		{
			int field = 1;
			while (true)
			{
				line = in.readLine();
				if (line == null || line.equals("0 0") || line.equals("")) break;

				String[] tokens = line.trim().replaceAll("\\s+", " ").split("\\s+");
				length = Integer.parseInt(tokens[0]);
				width = Integer.parseInt(tokens[1]);

				int[][] data = format(in);

				answers.append(String.format("Field #%d:%n%s%n", field++, sweep(data)));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	public static int[][] format(BufferedReader in) throws IOException
	{
		int[][] ret = new int[length][width];

		String line = "";
		for (int y = 0; y < length; ++y)
		{
			line = in.readLine().trim();

			line = line.replaceAll("\\s+", "");
			line = line.replace('.', '0');
			line = line.replaceAll("\\*", "-1");

			char[] data = line.toCharArray();
			int x = 0;
			for (int i = 0; i < data.length; ++i)
			{
				StringBuilder builder = new StringBuilder();
				builder.append(data[i]);

				if (data[i] == '-')
				{
					builder.append(data[++i]);
				}

				ret[y][x++] = Integer.parseInt(builder.toString().replaceAll("\\s+", ""));
			}
		}

		return ret;
	}

	public static String sweep(int[][] data)
	{
		for (int y = 0; y < length; ++y)
		{
			for (int x = 0; x < width; ++x)
			{
				if (data[y][x] == -1)
				{
					search(data, x, y);
				}
			}
		}

		return graph(data);
	}

	public static void search(int[][] data, int x, int y)
	{
		for (int index = 0; index < 8; ++index)
		{
			int newX = dx[index] + x;
			int newY = dy[index] + y;
			if ((newX > -1 && newX < width) && (newY > -1 && newY < length)
				&& data[newY][newX] != -1)
			{
				data[newY][newX] += 1;
			}
		}
	}

	public static String graph(int[][] data)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; ++i)
		{
			for (int j = 0; j < width; ++j)
			{
				builder.append(data[i][j]);
			}
			builder.append(String.format("%n"));
		}

		return builder.toString().replaceAll("\\-1", "*");
	}
}

class Coords
{
	public int x;
	public int y;

	public Coords()
	{
		this.x = 0;
		this.y = 0;
	}

	public Coords(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}