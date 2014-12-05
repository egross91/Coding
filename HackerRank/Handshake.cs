using System;
using System.Text;

namespace HackerRank
{
	public partial class Handshake
	{
		static void Main(string[] args)
		{
			int tests = int.Parse(Console.ReadLine());
			var builder = new StringBuilder();

			for (int i = 0; i < tests; ++i)
				builder.AppendLine(Solve());

			Console.WriteLine(builder.ToString());
		}

		static string Solve()
		{
			int n = int.Parse(Console.ReadLine());
			int ret = 0;

			while (n >= 2)
			{
				ret += --n;
			}	

			return ret.ToString();
		}
	}
}