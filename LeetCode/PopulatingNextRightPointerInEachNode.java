/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        Queue<TreeLinkNode> currentLevel = new LinkedList<TreeLinkNode>();
        
        if (root != null)
            currentLevel.add(root);
            
        while (!currentLevel.isEmpty()) {
            Queue<TreeLinkNode> nextLevel = new LinkedList<TreeLinkNode>();
            Queue<TreeLinkNode> traverse  = new LinkedList<TreeLinkNode>(currentLevel);
            TreeLinkNode current;
            
            // getNextLevel();
            while (!traverse.isEmpty()) {
                current = traverse.poll();
                
                if (current != null) {
                    if (current.left != null)
                        nextLevel.add(current.left);
                    if (current.right != null)
                        nextLevel.add(current.right);
                }
            }
            
            current           = currentLevel.poll();
            TreeLinkNode next = null;
            
            // linkCurrentLevel();
            while (!currentLevel.isEmpty()) {
                next         = currentLevel.poll();
                current.next = next;
                current      = next;
            }
            
            if (current != null)
                current.next = null;
            
            currentLevel = nextLevel;
        }
    }
}