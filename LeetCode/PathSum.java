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
    public boolean hasPathSum(TreeNode root, int sum) {
        return hasPathSum(root, sum, 0);
    }
    
    private boolean hasPathSum(TreeNode root, int goalSum, int currentSum) {
        if (root == null)
            return false;
            
        int newSum = currentSum + root.val;
        boolean leftHasSum  = false;
        boolean rightHasSum = false;
        
        if (root.left == null && root.right == null)
            return (newSum == goalSum);
        if (root.right != null)
            leftHasSum = hasPathSum(root.right, goalSum, newSum);
        if (root.left != null)
            rightHasSum = hasPathSum(root.left, goalSum, newSum);
        
        return (leftHasSum || rightHasSum);
    }
}