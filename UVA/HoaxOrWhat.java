import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        String line = "";
        while ((line = in.readLine()) != null) {
            int days = Integer.parseInt(line);
            if (days == 0) break;

            long cost = 0;
            TreeSet<PseudoNumber> bills = new TreeSet<PseudoNumber>(new PseudoNumberComparator());

            for (int i = 1; i <= days; ++i) {
                String[] dayData = in.readLine().split(" ");
                int dayBills = Integer.parseInt(dayData[0]);

                for (int j = 1; j <= dayBills; ++j) {
                    int value = Integer.parseInt(dayData[j]);

                    bills.add(new PseudoNumber(value, i*j));
                }

                int max = bills.pollLast().value;
                int min = bills.pollFirst().value;
                long diff = max - min;

                // System.out.printf("m: %d | m: %d | d: %d%n", max, min, diff);
                // System.out.printf("s: %s%n", bills.toString());

                cost += diff;
            }

            out.append(String.format("%s%n", cost));
        }

        System.out.print(out);
	}

    private static void tryToAddExtras(TreeSet<Long> set, Queue<Long> extras) {
        Queue<Long> tempQueue = new LinkedList<Long>();

        while (!extras.isEmpty()) {
            long extra = extras.poll();

            if (!set.add(extra)) {
                tempQueue.offer(extra);
            }
        }

        extras.addAll(tempQueue);
    }

    private static class PseudoNumber {
        public final int value;
        public final int index;

        private PseudoNumber(int v, int i) {
            this.value = v;
            this.index = i;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.value, this.index);
        }
    }

    private static class PseudoNumberComparator implements Comparator<PseudoNumber> {
        @Override
        public int compare(PseudoNumber lhs, PseudoNumber rhs) {
            int valueComparison = Integer.compare(lhs.value, rhs.value);
            
            return ((valueComparison == 0) ? Integer.compare(lhs.index, rhs.index) : valueComparison);
        }
    }
}