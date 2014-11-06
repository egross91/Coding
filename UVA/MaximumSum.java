import java.util.*;
import java.io.*;

class Main
{
	public static void main(String[] args) throws Exception
	{
		// BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		Scanner in = new Scanner(System.in);
		StringBuilder builder = new StringBuilder();

		String line;
		try
		{
			while (in.hasNext())
			{
				int N = in.nextInt();

				int[][] matrix = formatInput(N, in);

				System.out.println(solve(N, matrix));
			}
		}
		catch (Exception e)
		{
			System.exit(1);
		}
	}

	private static int[][] formatInput(int n, Scanner in)
	{
		int[][] ret = new int[n][n];

		for (int r = 0; r < n; ++r)
			for (int c = 0; c < n; ++c)
				ret[r][c] = in.nextInt();

		return ret;
	}

	private static int solve(int N, int[][] matrix)
	{
		int max = Integer.MIN_VALUE;

		for (int c = 0; c < N; ++c)
		{
			int[] temp = new int[N];

			for (int c2 = c; c2 < N; ++c2)
			{
				for (int r = 0; r < N; ++r)
					temp[r] += matrix[r][c2];

				int sum = Kadane(temp, N);

				max = (max > sum) ? max : sum;
			}
		}

		return max;
	}

	private static int Kadane(int[] temp, int N)
	{
		int maxeh, maxsf;
		maxeh = maxsf = -1270000;

		for (int i = 0; i < N; ++i)
		{
			maxeh = Math.max(maxeh + temp[i], temp[i]);
			maxsf = Math.max(maxsf, maxeh);
		}

		return maxsf;
	}
}


// int rowSum;
// int storedSum;
// for (int r = 0; r < N; ++r)
// {
// 	for (int colLimit = 0; colLimit < N-1; ++colLimit)
// 	{
// 		rowSum = 0;
// 		storedSum = 0;
// 		for (int c = 0; c <= colLimit; ++c)
// 		{
// 			max = Math.max(max, matrix[r][c]);
// 			rowSum += matrix[r][c];
// 			storedSum += matrix[r][c];
// 		}
// 		max = Math.max(max, rowSum);
//
// 		for (int r2 = 0; r2 <= r-1; ++r2)
// 		{
// 			for (int c = 0; c <= colLimit; ++c)
// 			{
// 				storedSum += matrix[r2][c];
// 			}
// 		}
//
// 		max = Math.max(max, storedSum);
// 	}
// }
//
// for (int r = 0; r < N; ++r)
// {
// 	for (int colLimit = 0; colLimit < N; ++colLimit)
// 	{
// 		rowSum = 0;
// 		storedSum = 0;
// 		for (int c = colLimit; c < N; ++c)
// 		{
// 			max = Math.max(max, matrix[r][c]);
// 			rowSum += matrix[r][c];
// 			storedSum += matrix[r][c];
// 		}
//
// 		max = Math.max(max, rowSum);
//
// 		for (int r2 = 0; r2 <= r-1; ++r2)
// 		{
// 			for (int c = colLimit; c < N; ++c)
// 			{
// 				storedSum += matrix[r2][c];
// 			}
// 		}
//
// 		max = Math.max(max, storedSum);
// 	}
// }
//
// for (int c = 0; c < N; ++c)
// {
// 	for (int rowLimit = 0; rowLimit < N-1; ++rowLimit)
// 	{
// 		rowSum = 0;
// 		storedSum = 0;
// 		for (int r = 0; r <= rowLimit; ++r)
// 		{
// 			max = Math.max(max, matrix[r][c]);
// 			rowSum += matrix[r][c];
// 			storedSum += matrix[r][c];
// 		}
// 		max = Math.max(max, rowSum);
//
// 		for (int c2 = 0; c2 <= c-1; ++c2)
// 		{
// 			for (int r = 0; r <= rowLimit; ++r)
// 			{
// 				storedSum += matrix[r][c2];
// 			}
// 		}
//
// 		max = Math.max(max, storedSum);
// 	}
// }
//
// for (int c = 0; c < N; ++c)
// {
// 	for (int rowLimit = 0; rowLimit < N; ++rowLimit)
// 	{
// 		rowSum = 0;
// 		storedSum = 0;
// 		for (int r = rowLimit; r < N; ++r)
// 		{
// 			max = Math.max(max, matrix[r][c]);
// 			rowSum += matrix[r][c];
// 			storedSum += matrix[r][c];
// 		}
//
// 		max = Math.max(max, rowSum);
//
// 		for (int c2 = 0; c2 <= c-1; ++c2)
// 		{
// 			for (int r = rowLimit; r < N; ++r)
// 			{
// 				storedSum += matrix[r][c2];
// 			}
// 		}
//
// 		max = Math.max(max, storedSum);
// 	}
// }
//
