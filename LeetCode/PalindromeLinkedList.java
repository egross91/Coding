/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null)
            return true;
        
        ListNode tortoise = head;
        ListNode hare     = head;
        ListNode result   = isPalindrome(tortoise, hare);
        
        return (result != null && result.val != Integer.MIN_VALUE);
    }
    
    private ListNode isPalindrome(ListNode tortoise, ListNode hare) {
        if (tortoise.next == null)
            return tortoise;
        if (hare == null)
            return tortoise;
        if (hare.next == null)
            return tortoise.next;
        
        ListNode toCompare = isPalindrome(tortoise.next, hare.next.next);
        
        if (toCompare == null)
            return tortoise;
        if (toCompare.val != tortoise.val)
            return new ListNode(Integer.MIN_VALUE);
        
        return ((toCompare.next != null) ? toCompare.next : tortoise);
    }
}