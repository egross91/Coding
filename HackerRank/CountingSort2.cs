using System;
using System.Text;

namespace HackerRank
{
	public partial class CountingSort2
	{
		static void Main(string[] args)
		{
			int n = int.Parse(Console.ReadLine());

			Console.WriteLine(Solve(n));
		}
		static string Solve(int n)
		{
			var builder = new StringBuilder();
			string[] numbers = Console.ReadLine().Split(' ');

			int[] arr = new int[n];
			for (int i = 0; i < n; ++i)
				arr[i] = int.Parse(numbers[i]);

			Array.Sort(arr);

			for (int i = 0; i < n; ++i)
				builder.Append(arr[i] + " ");

			return builder.ToString();
		}
	}
}