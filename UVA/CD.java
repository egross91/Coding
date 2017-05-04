import java.io.*;
import java.util.*;
// AC
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        String line = "";
        while ((line = in.readLine()) != null) {
            String[] lineData = line.split(" ");
            int n = Integer.parseInt(lineData[0]);
            int m = Integer.parseInt(lineData[1]);
            if (n == 0 && m == 0) break;

            TreeSet<Integer> cds = new TreeSet<Integer>();
            for (int i = 0; i < n; ++i) cds.add(Integer.parseInt(in.readLine()));
            
            int dupes = 0;
            for (int i = 0; i < m; ++i) if (cds.contains(Integer.parseInt(in.readLine()))) ++dupes;

            out.append(String.format("%d%n", dupes));
        }

        System.out.print(out);
	}
}