/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// ACs
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (!find(root, p) || !find(root, q)) { return null; }
        
        return doLowestCommonAncestor(root, p, q);
    }
    
    private TreeNode doLowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) { return null; }
        if (root == q || root == p) { return root; }
        
        boolean pIsInLeft = find(root.left, p);
        boolean qIsInLeft = find(root.left, q);
        
        if (pIsInLeft && qIsInLeft) {
            return doLowestCommonAncestor(root.left, p, q);
        } else if (pIsInLeft != qIsInLeft) {
            return root;
        } else {
            return doLowestCommonAncestor(root.right, p, q);
        }
    }
    
    private boolean find(TreeNode root, TreeNode cand) {
        if (root == null) { return false; }
        
        if (root == cand) { return true; }
        
        return (find(root.left, cand) || find(root.right, cand));
    }
}