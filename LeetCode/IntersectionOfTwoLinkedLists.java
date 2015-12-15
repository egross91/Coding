/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int M = getLength(headA);
        int N = getLength(headB);
        int difference = Math.abs(M-N);
        
        if (M > N) 
            headA = traverseNSteps(headA, difference);
        else 
            headB = traverseNSteps(headB, difference);
        
        while (headA != null && headB != null) {
            if (headA == headB)
                return headA;
            
            headA = headA.next;
            headB = headB.next;
        }
        
        return null;
    }
    
    private ListNode traverseNSteps(ListNode node, int steps) {
        while (steps-- > 0)
            node = node.next;
        
        return node;
    }
    
    private int getLength(ListNode root) {
        if (root == null)
            return 0;
        
        return getLength(root.next) + 1;
    }
}