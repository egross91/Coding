import java.math.*;

public class Solution {
    public String addBinary(String a, String b) {
        BigInteger bigA = new BigInteger(a, 2);
        BigInteger bigB = new BigInteger(b, 2);
        BigInteger sum  = bigA.add(bigB);
        
        return sum.toString(2);
    }
}