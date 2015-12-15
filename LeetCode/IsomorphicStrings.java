public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length())
            return false;
        
        // Keep track of the number of new letters appears.
        int[] sSequence = getSequence(s);
        int[] tSequence = getSequence(t);
        
        return Arrays.equals(sSequence, tSequence);
    }
    
    private int[] getSequence(String s) {
        int pointer = 1;
        int[] charVal  = new int[256];
        int[] sequence = new int[s.length()];
        for (int i = 0; i < s.length(); ++i) {
            int charIndex = (int)s.charAt(i);
            
            if (charVal[charIndex] == 0)
                charVal[charIndex] = pointer++;
            
            sequence[i] = charVal[charIndex];
        }
        
        return sequence;
    }
}