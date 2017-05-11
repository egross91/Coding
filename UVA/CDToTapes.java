import java.io.*;
import java.util.*;
// AC
class Main {
    private static SumList best;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader in = new BufferedReader(new FileReader("CDToTapes.in"));
		StringBuilder out = new StringBuilder();
		
        String line = "";

        while ((line = in.readLine()) != null) {
            if (line.trim().equals("")) { break; }
            
            String[] lineData = line.replaceAll("\\s+", " ").split(" ");

            int N            = Integer.parseInt(lineData[0]);
            int numDurations = Integer.parseInt(lineData[1]);
            int[] durations  = new int[lineData.length-2];

            for (int i = 2; i < lineData.length; ++i) { durations[i-2] = Integer.parseInt(lineData[i]); }

            out.append(String.format("%s%n", solve(N, durations)));
        }

        System.out.print(out);
	}

    private static String solve(int N, final int[] durations) {
        best = new SumList(N);

        for (int i = 0; i < durations.length; ++i) {
            SumList list = new SumList(durations.length);

            list.add(durations[i]);

            if (backtrack(N, i, durations, list)) {
                return list.toString();
            }
        }

        return best.toString();
    }

    private static boolean backtrack(int N, int index, final int[] durations, SumList list) {
        if (list.getListSum() == N) { 
            return true; 
        } else if (list.getListSum() > best.getListSum()) {
            best = null;
            best = new SumList(list);
        }

        for (int i = index + 1; i < durations.length; ++i) {
            if (durations[i] + list.getListSum() <= N) {
                // System.out.printf("[%d]:%d%n", i, durations[i]);
                // System.out.printf("%s%n", list);

                list.add(durations[i]);
                // System.out.printf("%s%n", list);
                // System.out.printf("%s%n", best);

                if (list.getListSum() == N) {
                    return true;
                } else if (list.getListSum() > best.getListSum()) {
                    best = null;
                    best = new SumList(list);
                }

                if (backtrack(N, i, durations, list)) {
                    return true;
                } else if (list.getListSum() > best.getListSum()) {
                    best = null;
                    best = new SumList(list);
                }

                list.removeLast();

                if (list.getListSum() == N) {
                    return true;
                } else if (list.getListSum() > best.getListSum()) {
                    best = null;
                    best = new SumList(list);
                }
            }
        }

        return false;
    }

    private static class SumList {
        private int[] values;
        private int sum;
        private int valuesPtr = 0;

        public SumList(int N) {
            this.values = new int[N];
            this.sum    = 0;
        }

        public SumList(SumList sl) {
            this.values    = Arrays.copyOf(sl.getValues(), sl.getValues().length);
            this.sum       = sl.getListSum();
            this.valuesPtr = sl.getValuesPointer();
        }

        public void add(Integer value) {
            this.sum += value;
            this.values[valuesPtr++] = value;
        }

        public void removeLast() {
            Integer value = this.values[--valuesPtr];
            this.sum -= value;
        }

        public int getListSum() {
            return this.sum;
        }

        private int[] getValues() {
            return this.values;
        }

        private int getValuesPointer() {
            return this.valuesPtr;
        }

        @Override
        public String toString() {
            StringBuilder valuesBuilder = new StringBuilder();

            if (this.valuesPtr >= 1) {
                valuesBuilder.append(this.values[0]);
            }

            for (int i = 1; i < valuesPtr; ++i) {
                valuesBuilder.append(String.format(" %d", this.values[i]));
            }

            return String.format("%s sum:%d", valuesBuilder, this.sum);
        }
    }
}