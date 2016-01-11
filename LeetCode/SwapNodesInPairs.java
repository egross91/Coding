/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode headPtr = swapPairs(head, head.next);
        
        return headPtr;
    }
    
    private ListNode swapPairs(ListNode current, ListNode next) {
        if (current == null)
            return null;
        else if (next == null)
            return current;
        else {
            ListNode save = next.next;
            next.next     = current;
            
            if (save != null && save.next != null) 
                current.next = swapPairs(save, save.next);
            else if (save != null)
                current.next = swapPairs(save, null);
            else 
                current.next = null;
                
            return next;
        }
    }
}