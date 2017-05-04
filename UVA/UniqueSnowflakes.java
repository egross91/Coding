import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
        int tests = Integer.parseInt(in.readLine());

        while (tests-- > 0) {
            TreeMap<Integer, Integer> indices = new TreeMap<Integer, Integer>();
            int n = Integer.parseInt(in.readLine().trim());
            int biggestBag = 0;
            int indexOfLastUnique = 0;

            for (int i = 0; i < n; ++i) {
                Integer snowflake = Integer.parseInt(in.readLine().trim());
                Integer index = indices.get(snowflake);

                if (index == null) {
                    biggestBag = Math.max(biggestBag, (i - indexOfLastUnique + 1));
                }
                else {
                    biggestBag = Math.max(biggestBag, (i - indexOfLastUnique));
                    indexOfLastUnique = Math.max(index + 1, indexOfLastUnique);
                }

                // System.out.printf("i: %d | n: %d | u: %d | b: %d%n", i, snowflake, indexOfLastUnique, biggestBag);

                indices.put(snowflake, i);
            }

            out.append(String.format("%d%n", biggestBag));
        }

        System.out.print(out);
	}
}