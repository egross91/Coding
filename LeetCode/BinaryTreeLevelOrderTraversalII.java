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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels  = new LinkedList<List<Integer>>();
        if (root == null)
            return levels;
        
        List<TreeNode> currentLevel = new LinkedList<TreeNode>();
        
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            List<TreeNode> nextLevel = new LinkedList<TreeNode>();
            
            addLevel(levels, currentLevel);
            for (TreeNode node : currentLevel) {
                if (node != null) {
                    if (node.left != null)
                        nextLevel.add(node.left);
                    if (node.right != null)
                        nextLevel.add(node.right);
                }
            }
            
            currentLevel = nextLevel;
        }
        
        Collections.reverse(levels);
        return levels;
    }
    
    private void addLevel(List<List<Integer>> levels, List<TreeNode> level) {
        List<Integer> values = new LinkedList<Integer>();
        
        for (TreeNode node : level)
            if (node != null)
                values.add(node.val);
        
        levels.add(values);
    }
}