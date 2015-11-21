/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode(int x) : val(x), next(NULL) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        long long l1_val = extractValue(l1);
        long long l2_val = extractValue(l2);
        long long result = l1_val + l2_val;
        
        ListNode* node = createListWithNumber(result, -1);
        return node;
    }
    
private:
    long long extractValue(const ListNode* l) {
        if (l->next == NULL) {
            return l->val;
        }
            
        long long currentValue = extractValue(l->next);
        currentValue     = (currentValue * 10) + (long long)l->val;
        return currentValue;
    }
    
    ListNode* createListWithNumber(long long value, int digit) {
        if (value > 0) {
            long long newVal = value / 10;
            int dig          = value % 10;
            
            ListNode* node = new ListNode(dig);
            node->next     = createListWithNumber(newVal, dig);
            
            return node;
        }
        else if (digit == -1) {
            ListNode* node = new ListNode(0);
            return node;
        }
        else {
            return NULL;
        }
    }
};