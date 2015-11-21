public class Solution {
    public int[] plusOne(int[] digits) {
        final int N = digits.length;
        int toAdd;
        LinkedList<Integer> list = new LinkedList<Integer>();
        boolean carry = false;
        
        if (digits[N-1]+1 > 9) {
            list.addFirst(new Integer(0));
            carry = true;
        }
        else 
            list.addFirst(digits[N-1] + 1);
        
        for (int i = N-2; i >= 0; --i) {
            toAdd = digits[i];
            
            if (carry) {
                toAdd = (toAdd + 1) % 10;
                
                if (toAdd == 0) 
                    carry = true;
                else
                    carry = false;    
            }
            
            list.addFirst(toAdd);
        }
        
        if (carry)
            list.addFirst(new Integer(1));
        
        int[] ret = new int[list.size()];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = list.removeFirst();
        return ret;
    }
}