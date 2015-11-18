import java.util.*;
import java.io.*;

class Main {
	private static int N;
	private static Map<String, Integer> nameToInt;
	private static Map<Integer, String> intToName;

	public static void main(String[] args) throws FileNotFoundException {
		Scanner in = new Scanner(System.in);
		// Scanner in = new Scanner(new File("GreedyGiftGivers.in"));
		int counter = 0;

		while (true) {
			try {
				N = in.nextInt();
			} catch (Exception e) {
				break;
			}

			int[] values = new int[N];
			nameToInt    = new HashMap<String, Integer>();
			intToName    = new HashMap<Integer, String>();

			for (int i = 0; i < N; ++i) {
				String name = in.next();
				nameToInt.put(name, i);
				intToName.put(i, name);
			}

			for (int i = 0; i < N; ++i) {
				String giver  = in.next();
				int cost      = in.nextInt();
				int receivers = in.nextInt();

				if (receivers > 0) {
					int receiving = cost / receivers;
					int loss      = 0;

					for (int j = 0; j < receivers; ++j) {
						String receiver = in.next();
						int index       = nameToInt.get(receiver);

						values[index] += receiving;
						loss          += receiving;
					}

					values[nameToInt.get(giver)] -= loss;
				} 
			}

			if (counter > 0) System.out.println();
			for (int i = 0; i < N; ++i) {
				System.out.printf("%s %d\n", intToName.get(i), values[i]);
			}
			++counter;

			// if(in.hasNext())
			// 	System.out.println("\n");
		}
	}
}