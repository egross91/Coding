/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    private int index;
    private List<Integer> values = new LinkedList<Integer>();

    public BSTIterator(TreeNode root) {
        index = 0;
        
        if (root != null)
            buildList(root, values);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return (index < values.size());
    }

    /** @return the next smallest number */
    public int next() {
        return values.get(index++);
    }
    
    /** Does in-order traversal. **/
    private void buildList(TreeNode root, List<Integer> values) {
        if (root.left != null)
            buildList(root.left, values);
            
        values.add(root.val);
        
        if (root.right != null)
            buildList(root.right, values);
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */