/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        
        List<String> numbers = new LinkedList<String>();
        sumNumbers(root, new StringBuilder(), numbers);
        
        return sumStringValues(numbers);
    }
    
    private void sumNumbers(TreeNode node, StringBuilder builder, List<String> numbers) {
        builder.append(node.val);
        
        if (node.left == null && node.right == null) 
            numbers.add(builder.toString());
        else {
            if (node.left != null)
                sumNumbers(node.left, new StringBuilder(builder), numbers);
            if (node.right != null)
                sumNumbers(node.right, new StringBuilder(builder), numbers);
        }
    }
    
    private int sumStringValues(List<String> numbers) {
        int sum = 0;
        
        for (String val : numbers)
            sum += Integer.parseInt(val);
    
        return sum;
    }
}