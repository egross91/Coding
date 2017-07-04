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
    public int countNodes(TreeNode root) {
        if (root == null) { return 0; }
        
        int maxHeight        = getHeight(root, 0);
        int maxPossibleNodes = (1 << maxHeight) - 1;
        int numMissingNodes  = getNumMissingNodes(root, maxHeight);
        
        return maxPossibleNodes - numMissingNodes;
    }
    
    private int getHeight(TreeNode root, int height) {
        if (root == null) { return height; }
        
        return (getHeight(root.left, height+1));
    }
    
    private int getNumMissingNodes(TreeNode root, int height) {
        int low  = 1;
        int high = (1 << (height-1)) - 1;
        
        int maxMissing = high;
        
        while (low <= high) {
            int mid = (low + ((high - low) >> 1));
            int route = reverseBits(mid, height-1);
            
            // System.out.printf("low: %d | high: %d%n", low, high);
            // System.out.printf("mid: %d%n", mid);
            
            if (find(root, route, height-1) != null) {
                low = mid+1;
            } else {
                high = mid-1;
            }
        }
        
        // if (find(root, reverseBits(high, height-1), height-1) != null) {
        //     ++high;
        // }
        
        return maxMissing - high;
    }
    
    private TreeNode find(TreeNode root, int route, int height) {
        // System.out.println("route: " + route);
        
        while (height-- > 0) {
            if ((route & 0x1) == 0) {
                // System.out.println("left");
                root = root.left;
            } else {
                // System.out.println("right");
                root = root.right;
            }
            
            route >>= 1;
        }
        
        return root;
    }
    
    private int reverseBits(int value, int iterations) {
        int reverse = 0;
        
        while (iterations-- > 0) {
            reverse <<= 1;
            
            if ((value & 0x1) != 0) {
                reverse |= 1;
            }
            
            value >>= 1;
        }
        
        return reverse;
    }
}