/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low  = 1;
        int high = n;
        int mid;
         
        do {
            mid = (low + ((high - low) >> 1));
            
            if (isBadVersion(mid)) 
                high = mid-1;
            else
                low = mid+1;
        } while (low < high);
        
        return ((isBadVersion(low)) ? low : (low+1));
    }
}