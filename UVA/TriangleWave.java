import java.util.*;
import java.io.*;

class Main
{
	private static String[] amps = new String[15];
	private static boolean[] usedAmp = new boolean[15];

	public static void main(String[] args)
	{
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder answers = new StringBuilder();

		String line;
		try
		{
			line = in.readLine();

			int N = Integer.parseInt(line.replaceAll("\\s+", ""));

			for (int i = 0; i < N; ++i)
			{
				in.readLine();

				line = in.readLine();
				int amp = Integer.parseInt(line.trim());

				line = in.readLine();
				int freq = Integer.parseInt(line.trim());

				if (!usedAmp[amp])
				{
					amps[amp] = getWave(amp);
					usedAmp[amp] = true;
				}

				for (int j = 0; j < freq; ++j)
				{
					answers.append(amps[amp] + "\n");
				}
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}

		System.out.println(answers.toString().trim());
	}

	private static String getWave(int amp)
	{
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i <= amp; ++i)
		{
			for (int j = 0; j < i; ++j)
			{
				builder.append(i);
			}
			builder.append("\n");
		}

		for (int i = amp-1; i > 0; --i)
		{
			for (int j = i; j > 0; --j)
			{
				builder.append(i);
			}
			builder.append("\n");
		}

		return builder.toString();
	}
}