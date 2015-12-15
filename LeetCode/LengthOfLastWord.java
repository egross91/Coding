public class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        String[] data = s.split(" ");
        
        return data[data.length-1].length();
    }
}