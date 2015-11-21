public class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();
        List<Integer> row            = new ArrayList<Integer>();
        List<Integer> previous;
        int left, right;
    
        if (numRows >= 1) {
            // Start the top of the triangle.
            row.add(1);
            triangle.add(row);
            
            for (int i = 1; i < numRows; ++i) {
                previous = triangle.get(i-1);
                row      = new ArrayList<Integer>();
                
                for (int j = 0; j <= previous.size(); ++j) {
                    left = right = 0;
                    
                    // Get numbers from the previous row.
                    if (j-1 >= 0) 
                        left = previous.get(j-1);
                    if (j < previous.size())
                        right = previous.get(j);
                    
                    // Put into the row.
                    row.add(left + right);
                }
                
                triangle.add(row);
            }
        }
        
        return triangle;
    }
}