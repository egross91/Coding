/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    TreeNode* invertTree(TreeNode* root) {
        // Traverse (level-order) the tree until we reach a null node and/or level.
        queue<TreeNode*> Q;
        Q.push(root);
        
        TreeNode* temp;
        TreeNode* node;
        while (!Q.empty()) {
            node = Q.front(); Q.pop();
            
            temp = nullptr;
            if (node) {
                // Swap the left and right node of the current node.
                temp = node->right;
                node->right = node->left;
                node->left = temp;
                
                // For every level, we grab every node on that level.
                if (node->left)
                    Q.push(node->left);
                if (node->right)
                    Q.push(node->right);
            }
        }
        
        return root;
    }
};