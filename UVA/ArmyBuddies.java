import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		String[] lineData;
		char endChar = ' ';

		while (true) {
			lineData = null;
			lineData = in.readLine().split(" ");

			int S = Integer.parseInt(lineData[0]);
			int B = Integer.parseInt(lineData[1]);

			if (endChar == '-')
				out.append(String.format("%c%n", endChar));
			if (S == 0 && B == 0) break;

			int[] leftPointers = new int[S+1];
			int[] rightPointers = new int[S+1];
			for (int i = 1; i <= S; ++i) {
				leftPointers[i] = i-1;
				rightPointers[i] = i+1;
			}
			rightPointers[S] = 0;

			for (int i = 0; i < B; ++i) {
				lineData = null;
				lineData = in.readLine().split(" ");

				int left = Integer.parseInt(lineData[0]);
				int right = Integer.parseInt(lineData[1]);
				int leftPointer = leftPointers[left];
				int rightPointer = rightPointers[right];

				if (leftPointer == 0)
					out.append("*");
				else
					out.append(leftPointer);

				out.append(" ");

				if (rightPointer == 0)
					out.append("*");
				else
					out.append(rightPointer);

				leftPointers[rightPointer] = leftPointer;
				rightPointers[leftPointer] = rightPointer;

				out.append("\n");
			}

			endChar = '-';
		}

		System.out.print(out);
	}
}