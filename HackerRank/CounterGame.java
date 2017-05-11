import java.io.*;
import java.util.*;
import java.math.BigInteger;
// AC
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(in.readLine());
        
        while (T-- > 0) {
            BigInteger N = new BigInteger(in.readLine());
            
            System.out.println(solve(N));
        }
    }
    
    private static String solve(BigInteger N) {
        boolean turn = false; // false == Louise, true == Richard
        
        while (!N.equals(BigInteger.ONE)) {
            boolean isPowTwo = (N.and(N.subtract(BigInteger.ONE)).equals(BigInteger.ZERO));
            
            if (isPowTwo) {
                N = N.shiftRight(1);
            } else {
                N = N.subtract(getHighestPowerTwoLessThan(N));
            }
            
            if (N.equals(BigInteger.ONE)) break;
            
            turn = !turn;
        }
        
        return ((!turn) ? "Louise" : "Richard");
    }
    
    private static BigInteger getHighestPowerTwoLessThan(BigInteger N) {
        while (!N.and(N.subtract(BigInteger.ONE)).equals(BigInteger.ZERO)) {
            N = N.and(N.subtract(BigInteger.ONE));
        }
        
        return N;
    }
}