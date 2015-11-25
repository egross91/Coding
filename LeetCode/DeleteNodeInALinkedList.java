/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void deleteNode(ListNode node) {
        // Overwrite the value of the node that we want to delete.
        // Traverse to the end.
        ListNode previous = null;
        
        while (node.next != null) {
            node.val = node.next.val;
            
            previous = node;
            node     = node.next;
        }
        
        previous.next = null;
    }
}