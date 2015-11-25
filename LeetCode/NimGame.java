public class Solution {
    private static final int FOUR = 4;
    
    public boolean canWinNim(int n) {
        return (n % FOUR) != 0;
    }
}