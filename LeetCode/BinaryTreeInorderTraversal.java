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
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> path = new LinkedList<Integer>();
        doInOrderTraversal(root, path);
        
        return path;
    }
    
    private void doInOrderTraversal(TreeNode node, List<Integer> path) {
        if (node != null) {
            doInOrderTraversal(node.left, path);
            path.add(node.val);
            doInOrderTraversal(node.right, path);
        }
    }
}