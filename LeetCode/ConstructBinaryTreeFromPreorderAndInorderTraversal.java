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
    private int preorderPtr;
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || inorder.length != preorder.length) { return null; }
        
        this.preorderPtr = 0;
        
        return doBuildTree(preorder, inorder, 0, inorder.length);
    }
    
    private TreeNode doBuildTree(int[] preorder, int[] inorder, int low, int high) {
        if (low < high) {
            int i;
            for (i = low; i < high; ++i) {
                if (preorder[this.preorderPtr] == inorder[i]) { break; }
            }
            
            this.preorderPtr++;
            
            TreeNode root = new TreeNode(inorder[i]);
            root.left     = doBuildTree(preorder, inorder, low, i);
            root.right    = doBuildTree(preorder, inorder, i+1, high);
            
            return root;
        } else {
            return null;
        }
    }
}