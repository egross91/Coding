import java.util.*;
import java.io.*;
import java.math.BigInteger;

class Main
{
    private static BigInteger[] factorials = new BigInteger[101];

    public static void main(String[] args)
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answers = new StringBuilder();

        loadFactorials();

        String line;
        try
        {
            while (true)
            {
                line = in.readLine();
                if (line == null || line.equals("")) break;

                StringTokenizer tokens = new StringTokenizer(line);
                String N = tokens.nextToken();
                String M = tokens.nextToken();

                if (N.equals("0") && M.equals("0")) break;

                answers.append(N + " things taken " + M + " at a time is " + solve(N, M));
            }
        }
        catch (Exception e)
        {
            System.exit(1);
        }

        System.out.print(answers);
    }

    private static String solve(String n, String m)
    {
        int N = Integer.parseInt(n);
        int M = Integer.parseInt(m);

        BigInteger NminusM = factorials[N-M];
        if (NminusM.toString().equals("0"))
            return "1 exactly.\n";

        BigInteger mFact = factorials[M];
        BigInteger nFact = factorials[N];

        BigInteger result = nFact.divide(NminusM.multiply(mFact));

        return (result + " exactly.\n");
    }

    private static void loadFactorials()
    {
        factorials[0] = BigInteger.ZERO;
        factorials[1] = BigInteger.ONE;

        for (int i = 2; i <= 100; ++i)
        {
            factorials[i] = factorials[i-1].multiply(new BigInteger(Integer.toString(i)));
        }
    }
}
