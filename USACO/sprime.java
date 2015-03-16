/*
ID: eric.bg1
LANG: JAVA
TASK: sprime
*/

import java.util.*;
import java.io.*;
import java.math.*;

class sprime {
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("sprime.in"));
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));

        N = Integer.parseInt(in.readLine());
        String answers = solve();

        out.print(answers);
        out.close();
        System.exit(0);
    }

    private static String solve() {
        Set<Integer> ans = new HashSet<Integer>();
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        primes.add(5);
        primes.add(7);

        for (int i = 1; i < N; ++i) {
            for (int d = 1; d <= 9; ++d) {
                for (int j = 0; j < primes.size(); ++j) {
                    int current = primes.get(j);
                    current = current*10 + d;

                    if (isPrime(current)) {
                        primes.add(current);
                    }
                }
            }
        }

        Set<Integer> nums = new HashSet<Integer>();
        for (int i = 0; i < primes.size(); ++i) {
            if (Integer.toString(primes.get(i)).length() == N) {
                nums.add(primes.get(i));
            }
        }

        return format(nums);
    }

    // private static void dfs(int val, int bitmask, Set<Integer> answers) {
    //     if (bitmask == mask) {
    //         if (isSuperPrime(val)) {
    //             answers.add(val);
    //         }
    //     }
    //     else if (bitmask < mask) {
    //         int idx = 0;
    //         while (((1 << idx) & bitmask) == 1 && idx <= N) { ++idx; }
    //
    //         for (int i = 0; i <= 9; ++i) {
    //             int next = 1;
    //             if (i == 0 && idx == 0) {
    //                 continue;
    //             }
    //             else {
    //                 next = (((int)Math.pow(10, idx))*i) + val;
    //             }
    //
    //             System.out.println(next);
    //             dfs(next, bitmask | (1 << idx), answers);
    //         }
    //     }
    // }

    private static boolean isPrime(int val) {
        if (((val & 0x1) == 0) || val == 1) {
            return false;
        }

        int limit = (int)Math.sqrt(val)+1;
        for (int i = 2; i <= limit; ++i) {
            if (val%i == 0) {
                return false;
            }
        }

        return true;
    }

    private static String format(Set<Integer> nums) {
        ArrayList<Integer> ans = new ArrayList<Integer>(nums);
        Collections.sort(ans);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < ans.size(); ++i) {
            builder.append(ans.get(i) + "\n");
        }

        return builder.toString();
    }
}
