import java.util.*;
import java.io.*;
import java.math.*;

class WinningAtSports {
    private static BigInteger[] factorials = new BigInteger[4001];
    private final static BigInteger MODULO = BigInteger.valueOf(1000000007);

    public static void main(String[] args) throws IOException {
        // Scanner in = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();
        getFactorials();

        int tests = Integer.parseInt(in.readLine());
        for (int t = 1; t <= tests; ++t) {
            String[] data = in.readLine().split("-");
            int left = Integer.parseInt(data[0]);
            int right = Integer.parseInt(data[1]);

            answers.append("Case #" + t + ": " + process(left, right) + "\n");
        }

        System.out.print(answers);
        System.exit(0);
    }

    private static void getFactorials() {
        factorials[0] = BigInteger.ZERO;
        factorials[1] = BigInteger.ONE;

        for (int i = 2; i <= 4000; ++i)
            factorials[i] = factorials[i-1].multiply(BigInteger.valueOf(i));
    }

    private static String process(int left, int right) {
        BigInteger leftTwiceBI = factorials[left+right];
        BigInteger leftBI = factorials[left];
        BigInteger stressless = leftTwiceBI.divide(leftBI.multiply(leftBI));

        BigInteger stress = BigInteger.ZERO;
        if (right > 0) {
            BigInteger rightTwiceBI = factorials[2*right];
            BigInteger rightBI = factorials[right];
            stress = rightTwiceBI.divide(rightBI.multiply(rightBI)).divide(BigInteger.valueOf(right+1)).mod(MODULO);
        }

        String ret = String.valueOf(stressless.longValue()) + " " + String.valueOf(stress.longValue());
        return ret;
    }
}
