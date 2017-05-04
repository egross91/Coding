import java.io.*;
import java.util.*;
// AC
@SuppressWarnings("unchecked")
class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder out = new StringBuilder();
		
        int tests = Integer.parseInt(in.readLine());
        while (tests-- > 0) {
            String[] lineData = in.readLine().split(" ");
            int battlegrounds = Integer.parseInt(lineData[0]);
            int greenLemmings = Integer.parseInt(lineData[1]);
            int blueLemmings = Integer.parseInt(lineData[2]);

            PriorityQueue<Integer> greens = new PriorityQueue(greenLemmings, new DescendingComparator());
            PriorityQueue<Integer> blues = new PriorityQueue(blueLemmings, new DescendingComparator());

            for (int i = 0; i < greenLemmings; ++i) greens.add(Integer.parseInt(in.readLine()));
            for (int i = 0; i < blueLemmings; ++i) blues.add(Integer.parseInt(in.readLine()));

            // while (!greens.isEmpty()) {
            //     System.out.println(greens.poll());
            // }
            out.append(solve(battlegrounds, blues, greens));

            if (tests > 0) {
                out.append("\n");
            }
        }

        System.out.print(out);            
	}

    private static String solve(int battlegrounds, PriorityQueue<Integer> blues, PriorityQueue<Integer> greens) {
        while (!blues.isEmpty() && !greens.isEmpty()) {
            Queue<Integer> blueBattlers = new LinkedList<Integer>();
            Queue<Integer> greenBattlers = new LinkedList<Integer>();

            for (int i = 0; i < battlegrounds && !blues.isEmpty() && !greens.isEmpty(); ++i) {
                blueBattlers.add(blues.poll());
                greenBattlers.add(greens.poll());
            }

            while (!blueBattlers.isEmpty()) {
                int blueBattler = blueBattlers.poll();
                int greenBattler = greenBattlers.poll();
                int verdict = blueBattler - greenBattler;
                // System.out.printf("v: %d%n", verdict);

                if (verdict < 0) {
                    greens.offer(greenBattler - blueBattler);
                }
                else if (verdict > 0) {
                    blues.offer(verdict);
                }
            }
        }

        StringBuilder builder = new StringBuilder();

        if (blues.isEmpty() && greens.isEmpty()) {
            builder.append("green and blue died\n");
        }
        else if (blues.isEmpty()) {
            builder.append("green wins\n");

            while (!greens.isEmpty()) {
                builder.append(String.format("%d%n", greens.poll()));
            }
        }
        else {
            builder.append("blue wins\n");

            while (!blues.isEmpty()) {
                builder.append(String.format("%d%n", blues.poll()));
            }
        }

        return builder.toString();
    }

    private static class DescendingComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer lhs, Integer rhs) {
            return - (Integer.compare(lhs, rhs));
        }
    }
}