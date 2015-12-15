/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode headPtr = head;
        int result = removeNthFromEndImpl(headPtr, n);
        if (result == -1)
            return null;
        else if ((result + 1) == n) // The head is what we want to remove.
            headPtr = headPtr.next;
        
        
        return headPtr;
    }
    
    private int removeNthFromEndImpl(ListNode node, int n) {
        if (node == null)
            return -1;
        
        int length = removeNthFromEndImpl(node.next, n) + 1;
        if (length == n) 
            node.next = node.next.next;
        
        return length;
    }
}