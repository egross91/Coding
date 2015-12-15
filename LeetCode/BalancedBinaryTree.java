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
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        
        int leftHeight  = 0;
        int rightHeight = 0;
        
        if (root.left != null) 
            leftHeight = getHeight(root.left);
        if (root.right != null) 
            rightHeight = getHeight(root.right);
            
        if (Math.abs(leftHeight - rightHeight) <= 1) {
            boolean isLeftTreeBalanced  = isBalanced(root.left);
            boolean isRightTreeBalanced = isBalanced(root.right);
            // System.out.printf("val(%d) - left: %d\tright: %d\n", root.val, leftHeight, rightHeight);
            
            return (isLeftTreeBalanced && isRightTreeBalanced);
        }
        else 
            return false;
    }
    
    private int getHeight(TreeNode root) {
        if (root == null)
            return 0;
        
        int leftHeight  = 1;
        int rightHeight = 1;
        
        if (root.left != null)
            leftHeight = getHeight(root.left) + 1;
        if (root.right != null)
            rightHeight = getHeight(root.right) + 1;
        
        return Math.max(leftHeight, rightHeight);
    }
}
