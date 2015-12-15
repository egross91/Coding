public class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> currentRow = new ArrayList<Integer>(); 
        currentRow.add(1);
        
        for (int i = 1; i <= rowIndex; ++i) {
            List<Integer> nextRow = new ArrayList<Integer>();
            
            for (int j = 0; j <= currentRow.size(); ++j) {
                int left  = 0;
                int right = 0;
                
                if ((j-1) >= 0) 
                    left = currentRow.get(j-1);
                if (j < currentRow.size()) 
                    right = currentRow.get(j);
                
                nextRow.add(left+right);
            }
            
            currentRow = nextRow;
        }
        
        return currentRow;
    }
}
