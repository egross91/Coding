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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> levels  = new LinkedList<List<Integer>>();
        if (root == null)
            return levels;
        
        List<TreeNode> currentLevel = new LinkedList<TreeNode>();
        currentLevel.add(root);
        while (!currentLevel.isEmpty()) {
            List<TreeNode> nextLevel = new LinkedList<TreeNode>();
            levels.add(convertTreeNodesToInts(currentLevel));
            
            while (!currentLevel.isEmpty()) {
                TreeNode current = currentLevel.remove(0);
                
                if (current != null) {
                    if (current.left != null)
                        nextLevel.add(current.left);
                    if (current.right != null)
                        nextLevel.add(current.right);
                }
            }
            
            currentLevel = nextLevel;
        }
        
        return levels;
    }
    
    private List<Integer> convertTreeNodesToInts(List<TreeNode> l) {
        List<Integer> values = new LinkedList<Integer>();
        
        for (TreeNode n : l)
            if (n != null)
                values.add(n.val);
        
        return values;
    }
}