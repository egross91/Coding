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
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        
        return buildTree(nums, 0, nums.length-1);
    }
    
    private TreeNode buildTree(int[] nums, int low, int high) {
        TreeNode current = null;
        
        int mid = (low + ((high - low) >> 1));
        current = new TreeNode(nums[mid]);
        
        if (mid > low) 
            current.left = buildTree(nums, low, mid-1);
        else 
            current.left = null;
        
        if (mid < high) 
            current.right = buildTree(nums, mid+1, high);
        else 
            current.right = null;
        
        return current;
    }
}