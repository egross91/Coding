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
    vector<int> rightSideView(TreeNode* root) {
        vector<int> result;
        list<TreeNode*> currentLevel;
        
        if (root)
            currentLevel.push_back(root);
        
        while (!currentLevel.empty()) {
            result.push_back(currentLevel.back()->val);
            
            list<TreeNode*> nextLevel;
            
            while (!currentLevel.empty()) {
                TreeNode* current = currentLevel.front(); currentLevel.pop_front();
                
                if (current->left)
                    nextLevel.push_back(current->left);
                if (current->right)
                    nextLevel.push_back(current->right);
            }
            
            currentLevel = nextLevel;
        }
        
        return result;
    }
};