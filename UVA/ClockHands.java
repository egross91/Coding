import java.util.*;
import java.io.*;

class Main
{
	private static final double SIXTY = 60D;
	private static final double HALF = (double)1 / 2;

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
				if (line == null || line.equals("0:00") || line.equals("")) break;

				String[] time = line.trim().split(":");

				answers.append(String.format("%.3f%n", solve(time)));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}

		System.out.print(answers.toString());
	}

	private static double solve(String[] time)
	{
		double minute = Double.parseDouble(time[1]);
		double hour = Double.parseDouble(time[0]);

		double hourAngle = getHourAngle(hour, minute);
		double minAngle = getMinAngle(minute);

		double answer = (Double.compare(hourAngle, minAngle) > 0) ? (hourAngle - minAngle) : (minAngle - hourAngle);
		if (Double.compare(answer, 180D) == 1)
			return 360D - answer;

		return answer;
	}

	private static double getHourAngle(double hour, double mins)
	{
		return (double)(HALF * ((hour * SIXTY) + mins));
	}

	private static double getMinAngle(double mins)
	{
		return (double)(6D * mins);
	}
}
