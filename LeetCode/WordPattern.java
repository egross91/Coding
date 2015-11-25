public class Solution {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null)
            return false;
        
        String[] data = str.split(" ");
        
        if (pattern.length() != data.length)
            return false;
            
        Map<Character, String> charToStr = new HashMap<Character, String>();
        Map<String, Character> strToChar = new HashMap<String, Character>();
        String currentStr, previousStr;
        Character currentChar, previousChar;
        
        for (int i = 0; i < data.length; ++i) {
            currentChar = pattern.charAt(i);
            currentStr  = data[i];
            
            previousStr  = charToStr.get(currentChar);
            previousChar = strToChar.get(currentStr);
            
            if ((previousStr != null && !previousStr.equals(currentStr)) ||
                (previousChar != null && !previousChar.equals(currentChar)))
                return false;
            
            charToStr.put(currentChar, currentStr);
            strToChar.put(currentStr, currentChar);
        }
        
        return true;
    }
}