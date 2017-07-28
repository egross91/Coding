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
    private int postorderPtr;
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) { return null; }
        
        this.postorderPtr = postorder.length-1;
        
        return doBuildTree(inorder, postorder, 0, postorder.length);
    }
    
    private TreeNode doBuildTree(int[] inorder, int[] postorder, int low, int high) {
        if (low < high) {
            int i;
            for (i = low; i < high; ++i) {
                if (postorder[this.postorderPtr] == inorder[i]) { break; }
            }
            
            this.postorderPtr--;
            
            TreeNode root = new TreeNode(inorder[i]);
            root.right    = doBuildTree(inorder, postorder, i+1, high);
            root.left     = doBuildTree(inorder, postorder, low, i);
            
            return root;
        } else {
            return null;
        }
    }
}