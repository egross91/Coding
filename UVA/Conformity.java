import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int n;
        
        while ((n = Integer.parseInt(in.readLine())) != 0) {
            TreeMap<String, Integer> frequencies = new TreeMap<String, Integer>();

            for (int i = 0; i < n; ++i) {
                String combo = in.readLine();
                String parsed = parse(combo);
                Integer occurrence = frequencies.get(parsed);

                occurrence = (occurrence == null) ? 0 : occurrence;
                occurrence += 1;

                frequencies.put(parsed, occurrence);
            }

            out.append(String.format("%d%n", getMostFrequentCount(frequencies)));
        }

        System.out.print(out);
	}

    private static String parse(String combo) {
        String[] comboData = combo.split(" ");
        int[] values = new int[comboData.length];

        for (int i = 0; i < values.length; ++i) values[i] = Integer.parseInt(comboData[i]);
        Arrays.sort(values);

        return Arrays.toString(values);
    }

    private static int getMostFrequentCount(TreeMap<String, Integer> frequencies) {
        Collection<Integer> values = frequencies.values();
        final int max = Collections.max(values);
        int count = 0;

        for (Integer value : values) {
            if (value == max) {
                count += value;
            }
        }

        return Math.max(count, max);
    }
}