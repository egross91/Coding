/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // Get rid of the 'vals' at the head.
        while (head != null && head.val == val) 
            head = head.next;
        
        ListNode headPtr = head;
        ListNode seeker  = head;
        while (seeker != null && seeker.next != null) {
            ListNode next = seeker.next;
            while (next != null && next.val == val)
                next = next.next;
            
            seeker.next = next;
            seeker      = seeker.next;
        }
        
        return headPtr;
    }
}