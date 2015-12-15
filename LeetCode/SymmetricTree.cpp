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
    bool isSymmetric(TreeNode* root) {
        if (!root)
            return true;
        
        list<TreeNode*> currentLevel;
        currentLevel.push_back(root);
        
        while (!currentLevel.empty()) {
            list<TreeNode*> nextLevel;
            TreeNode* left;
            TreeNode* right;
            
            while (!currentLevel.empty()) {
                left  = currentLevel.front();
                right = currentLevel.back();
                currentLevel.pop_back(); 
                
                if (!currentLevel.empty())
                    currentLevel.pop_front();
                
                if ((!left && right) || (!right && left)) 
                    return false;
                else if (left && right) {
                    if (left->val != right->val) 
                        return false;
                    
                    nextLevel.push_front(left->right);
                    nextLevel.push_front(left->left);
                    nextLevel.push_back(right->left);
                    nextLevel.push_back(right->right);
                }
            }
            
            currentLevel = nextLevel;
        }
        
        return true;
    }
};
