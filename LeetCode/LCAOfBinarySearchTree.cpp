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
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
        TreeNode* result = lowestCommonAncestor(root, p, q, root);
        
        return (result) ? result : root;
    }
    
private:
    TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q, TreeNode* current) {
        if ((p->val <= current->val && q->val >= current->val) ||
            (p->val >= current->val && q->val <= current->val)) 
            return current;
        
        TreeNode* result = nullptr;
        if (current->right)
            result = lowestCommonAncestor(root, p, q, current->right);
        if (result)
            return result;
        
        if (current->left)
            result = lowestCommonAncestor(root, p, q, current->left);
        if (result)
            return result;
        
        return result;
    }
};