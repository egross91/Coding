import java.util.*;
import java.io.*;
import java.math.*;

class Main {
    private static long p, q;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        p = in.nextInt();
        q = in.nextInt();

        System.out.println(solve());
    }

    private static String solve() {
        StringBuilder builder = new StringBuilder();

        while (p <= q) {
            if (isKaprekar(p++)) {
                builder.append((p-1) + " ");
            }
        }

        String ret = builder.toString();
        return (ret.equals("")) ? "INVALID RANGE" : ret;
    }

    private static boolean isKaprekar(long v) {
        BigInteger val = BigInteger.valueOf(v).pow(2);
        String str = val.toString();

        int mid = str.length() >> 1;
        String left = str.substring(0, mid);
        String right = str.substring(mid, str.length());

        long l = Long.parseLong((left.equals("")) ? "0" : left);
        long r = Long.parseLong((right.equals("")) ? "0" : right);


        return ((l+r) == v);
    }
}
