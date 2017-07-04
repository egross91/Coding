/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
 // AC
public class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) { return true; }
        
        Stack<Integer> values = new Stack<Integer>();
        ListNode tortoise     = head;
        ListNode hare         = head;
        int length            = 1;
        
        do {
            values.push(tortoise.val);
            ++length;
            
            tortoise = tortoise.next;
            hare     = hare.next;
            
            if (hare != null) {
                ++length;
                hare = hare.next;
            }
        } while (hare != null);
        
        if ((length & 0x1) == 0 && length > 2) {
            values.pop();
        }
        
        while (tortoise != null) {
            int value = values.pop();
            
            if (value != tortoise.val) { return false; }
            
            tortoise = tortoise.next;
        }
        
        return true;
    }
}