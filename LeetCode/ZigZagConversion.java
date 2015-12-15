public class Solution {
    public String convert(String s, int numRows) {
        List<List<Character>> zigZag = new LinkedList<List<Character>>();
        int currentChar              = 0;
        final int length             = s.length();
        
        while (currentChar < length) {
            for (int i = 0; i < numRows && currentChar < length; ++i) {
                if (i >= zigZag.size())
                    zigZag.add(new LinkedList<Character>());
                
                zigZag.get(i).add(s.charAt(currentChar++));
            }
            
            for (int i = numRows-2; i >= 1 && currentChar < length; --i)
                zigZag.get(i).add(s.charAt(currentChar++));
        }
        
        StringBuilder builder = new StringBuilder();
        // System.out.printf("size: %d\n", zigZag.size());
        for (List<Character> row : zigZag)
            for (Character c : row)
                builder.append(c);
        
        return builder.toString();
    }
}