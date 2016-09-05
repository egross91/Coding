import java.io.*;
import java.util.*;
import java.math.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] lineData = in.readLine().split(" ");
		Point vasiliy = new Point(Double.parseDouble(lineData[0]), Double.parseDouble(lineData[1]));
		final int N = Integer.parseInt(in.readLine());
		double answer = Double.MAX_VALUE;

		for (int i = 0; i < N; ++i) {
			lineData = in.readLine().split(" ");
			double x = Double.parseDouble(lineData[0]);
			double y = Double.parseDouble(lineData[1]);
			double v = Double.parseDouble(lineData[2]);
			double t = vasiliy.distanceTo(new Point(x, y)) / v;

			answer = Math.min(t, answer);
		}

		System.out.println(new BigDecimal(answer).setScale(20, RoundingMode.HALF_UP));
	}

	private static class Point {
		public final double x;
		public final double y;

		public Point(double x, double y) {
			this.x = x;
			this.y = y;
		}

		private double distanceTo(Point p) {
			double x1 = this.x;
			double y1 = this.y;
			double x2 = p.x;
			double y2 = p.y;

			double a = x1 - x2;
			double b = y1 - y2;

			return Math.sqrt((a * a) + (b * b));
		}
	}
}