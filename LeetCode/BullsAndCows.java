public class Solution {
    public String getHint(String secret, String guess) {
        int[] secretLetterCount = new int[10];
        int A = 0; // Bulls
        
        // Count the number of Bulls & the letter count for non-bulls.
        for (int i = 0; i < secret.length(); ++i) {
            char g = guess.charAt(i);
            char s = secret.charAt(i);
            
            if (s == g) 
                ++A;
            else
                secretLetterCount[CharToInt(s)]++;
        }
        
        // Count the number of Cows based on non-Bulls.
        int B = 0; // Cows
        for (int i = 0; i < guess.length(); ++i) {
            char g = guess.charAt(i);
            char s = secret.charAt(i);
            
            if (s == g)
                continue;
            else {
                if (secretLetterCount[CharToInt(g)] > 0) {
                    ++B;
                    secretLetterCount[CharToInt(g)]--;
                }
            }
        }
        
        StringBuilder builder = new StringBuilder();
        
        builder.append(A);
        builder.append("A");
        builder.append(B);
        builder.append("B");
        
        return builder.toString();
    }
    
    private int CharToInt(char c) {
        return (int)(c - '0');
    }
}