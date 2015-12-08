/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        if (l2 == null)
            return l1;
        if (l1 == null)
            return l2;
        
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode result;
        
        if (p1.val < p2.val) {
            result = new ListNode(p1.val);
            p1   = p1.next;
        }
        else {
            result = new ListNode(p2.val);
            p2   = p2.next;
        }
        
        ListNode resultPtr = result;
        ListNode next;
        
        while (p1 != null && p2 != null) {
            next = null;
            
            if (p1.val < p2.val) {
                next = new ListNode(p1.val);
                p1   = p1.next;
            }
            else {
                next = new ListNode(p2.val);
                p2   = p2.next;
            }
            
            result.next = next;
            result      = next;
        }
        
        while (p1 != null) {
            next        = new ListNode(p1.val);
            result.next = next;
            
            p1     = p1.next;
            result = result.next; 
        }
        while (p2 != null) {
            next        = new ListNode(p2.val);
            result.next = next;
            
            p2     = p2.next;
            result = result.next;
        }
        
        return resultPtr;
    }
}