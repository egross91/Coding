/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode headPtr = head;
        ListNode here    = head;
        ListNode seeker;
        
        while (here != null) {
            int hereVal = here.val;
            seeker      = here;
            
            while (seeker != null && seeker.val == hereVal)
                seeker = seeker.next;
            
            here.next = seeker;
            here      = seeker;
        }
        
        return headPtr;
    }
}