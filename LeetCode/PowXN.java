// AC
public class Solution {
    public double myPow(double x, int n) {
        long longN = n;
        
        if (n == 0)  {
            return 1;
        } else if (n < 0) {
            return 1.0D /doMyPow(x, -longN);
        } else if (Double.compare(x, 0.0D) == 0) {
            return 0;
        } else {
            return doMyPow(x, n);
        }
    }
    
    private double doMyPow(double x, long n) {
        if (n == 1) {
            return x;
        } else if ((n & 0x1) == 0) {
            return doMyPow(x*x, n >> 1);
        } else {
            return x * doMyPow(x*x, (n >> 1));
        }
    }
}