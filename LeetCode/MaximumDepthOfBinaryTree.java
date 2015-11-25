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
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
            
        return maxDepthImp(root);
    }
    
    private int maxDepthImp(TreeNode root) {
        int height = 1;
        
        if (root == null)
            return height;
        
        if (root.left != null)
            height = Math.max(maxDepth(root.left)  + 1, height);
        if (root.right != null)
            height = Math.max(maxDepth(root.right) + 1, height);
            
        return height;
    }
}