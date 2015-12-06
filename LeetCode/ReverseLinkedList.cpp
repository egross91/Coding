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
    ListNode* reverseList(ListNode* head) {
        ListNode* current = head;
        ListNode* previous = nullptr;
        ListNode* next;
        
        while (current != nullptr) {
            next          = current->next;
            current->next = previous;
            previous      = current;
            current       = next;
        }
        
        return (current) ? current : previous;
    }
};