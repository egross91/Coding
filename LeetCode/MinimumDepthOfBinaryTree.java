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
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        
        int rightHeight = Integer.MAX_VALUE; 
        int leftHeight  = Integer.MAX_VALUE;
        if (root.right != null)
            rightHeight = minDepth(root.right) + 1;
        if (root.left != null)
            leftHeight = minDepth(root.left) + 1;
            
        return Math.min(leftHeight, rightHeight);
    }
}