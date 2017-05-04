import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int tests = Integer.parseInt(in.readLine());
        in.readLine(); // Read empty line.

        while (tests-- > 0) {
            TreeMap<String, Integer> trees = new TreeMap<String, Integer>();
            int totalTrees = 0;

            String tree = "";
            while ((tree = in.readLine()) != null && !tree.equals("")) {
                Integer treeCount = trees.get(tree);
                
                treeCount = (treeCount == null) ? 0 : treeCount;
                treeCount += 1;

                trees.put(tree, treeCount);
                ++totalTrees;
            }
            
            while (!trees.isEmpty()) {
                Map.Entry<String, Integer> treeEntry = trees.pollFirstEntry();
                double percentage = (double) ((double) treeEntry.getValue() / (double) totalTrees) * 100D;

                out.append(String.format("%s %3.4f%n", treeEntry.getKey(), percentage));
            }

            if (tests > 0)
                out.append("\n");
        }

        System.out.print(out);
	}
}