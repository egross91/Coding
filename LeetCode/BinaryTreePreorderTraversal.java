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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> preorder = new LinkedList<Integer>();
        doPreOrderTraversal(root, preorder);
        
        return preorder;
    }
    
    private void doPreOrderTraversal(TreeNode node, List<Integer> path) {
        if (node != null) {
            path.add(node.val);
            
            if (node.left != null)
                doPreOrderTraversal(node.left, path);
            if (node.right != null)
                doPreOrderTraversal(node.right, path);
        }
    }
}