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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new LinkedList<String>();
        getPaths(root, paths, "");
        
        return paths;
    }
    
    private void getPaths(TreeNode root, List<String> paths, String path) {
        if (root == null) 
            return;
        
        path += String.valueOf(root.val);
        if (root.left == null && root.right == null) 
            paths.add(path);
        else {
            if (root.left != null) 
                getPaths(root.left, paths, (path + "->"));
            if (root.right != null)
                getPaths(root.right, paths, (path + "->"));
        }
    }
}