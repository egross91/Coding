/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// AC
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) { return; }
        
        TreeNode seeker = root;
        
        while (seeker != null) {
            if (seeker.left != null) {
                TreeNode save = seeker.right;
                seeker.right  = seeker.left;
                seeker.left   = null;

                attachToRightmost(seeker, save);
            }
            
            seeker = seeker.right;
        }
    }
    
    private void attachToRightmost(TreeNode attachee, TreeNode toAttach) {
        TreeNode seeker = attachee;
        
        while (seeker.right != null) {
            seeker = seeker.right;
        }
        
        seeker.right = toAttach;
    }
}