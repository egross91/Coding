/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;
        
        // Tortoise & Hare.
        ListNode tortoise = head.next;
        ListNode hare     = head.next;
        if (hare != null)
            hare = hare.next;
        
        // Iterate through the list with them going at their respective paces.
        while (hare != null) {
            if (hare == tortoise)
                return true;
            
            tortoise = tortoise.next;
            hare     = hare.next;
            
            if (hare != null)
                hare = hare.next;
        }
        
        return false;
    }
}