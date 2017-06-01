import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    static long theGreatXor(long x){
        // Complete this function
        long largestPowTwo = x;
        
        // Get largest power of two from @param:x.
        while ((largestPowTwo & (largestPowTwo - 1)) != 0) {
            largestPowTwo &= (largestPowTwo - 1);
        }
        
        // Create number with all leading 1's and 0's following from the largest power of two.
        long toXor = 0xffffffffffffffffL ^ (largestPowTwo - 1);
        
        // Merge the largest power of two with leading 1's, then flip all of them to get correct answer.
        long answer = ~(x | toXor);
        
        return answer;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        for(int a0 = 0; a0 < q; a0++){
            long x = in.nextLong();
            long result = theGreatXor(x);
            System.out.println(result);
        }
    }
}
