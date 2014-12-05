using System;
using System.Text;

namespace HackerRank
{
	public partial class FindPoint
	{
		static void Main(string[] args)
		{
			int tests = int.Parse(Console.ReadLine());
			var builder = new StringBuilder();

			for (int i = 0; i < tests; ++i)
				builder.AppendLine(Solve());

			Console.WriteLine(builder.ToString());
		}

		private static string Solve()
		{
			String[] line = Console.ReadLine().Split(' ');
			int Px = int.Parse(line[0]);
			int Py = int.Parse(line[1]);
			int Qx = int.Parse(line[2]);
			int Qy = int.Parse(line[3]);

			int x = GetCoord(Px, Qx);
			int y = GetCoord(Py, Qy);

			return x.ToString() + " " + y.ToString();
		}

		private static int GetCoord(int coord1, int coord2)
		{
			int ret = coord2 - coord1;
			ret = ret + coord2;
			return ret;
		}
	}
}