import java.io.*;
import java.util.*;
// AC
class Main {
	private static List<Integer> powersOfTwo = new ArrayList<Integer>();

	static {
		int powOfTwo = 1;
		while (powOfTwo > 0) {
			powersOfTwo.add(powOfTwo);
			powOfTwo <<= 1;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		in.readLine();
		String[] lineData = in.readLine().split(" ");
		int[] numbers     = new int[lineData.length];
		Map<Integer, Set<Integer>> indexBuckets = new HashMap<Integer, Set<Integer>>();

		for (int i = 0; i < lineData.length; ++i) {
			numbers[i] = Integer.parseInt(lineData[i]);
			Set<Integer> bucket = indexBuckets.get(numbers[i]);

			if (bucket == null) {
				bucket = new HashSet<Integer>();
				bucket.add(i);

				indexBuckets.put(numbers[i], bucket);
			}
			else 
				bucket.add(i);
		}

		System.out.println(solve(numbers, indexBuckets));
	}

	private static long solve(final int[] nums, final Map<Integer, Set<Integer>> buckets) {
		long count = 0;

		for (int i = nums.length-1; i > 0; --i) {
			int val = nums[i];

			for (int k = 0; k < powersOfTwo.size(); ++k) {
				int powOfTwo  = powersOfTwo.get(k);

				if (powOfTwo < val) continue;
				else if (val == powOfTwo) {
					Set<Integer> powBucket = buckets.get(nums[i]);
					count += (powBucket.size()-1);
				}
				else {
					int diff = powOfTwo - val;
					Set<Integer> bucket = buckets.get(diff);

					if (bucket != null && diff != val)
						count += bucket.size();
				}
			}

			buckets.get(val).remove(i);
		}

		return count;
	}
}