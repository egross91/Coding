/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return head;
        
        ListNode headPtr  = head;
        ListNode oddPtr   = headPtr;
        ListNode evenPtr  = head.next;
        ListNode evenHead = evenPtr;
        
        while (oddPtr != null && oddPtr.next != null && evenPtr != null && evenPtr.next != null) {
            ListNode save = null;
            
            oddPtr.next = evenPtr.next;
            oddPtr = oddPtr.next;
            if (oddPtr != null) {
                save = oddPtr.next;
                oddPtr.next = evenHead;
            }
            if (evenPtr != null) {
                evenPtr.next = save;
                evenPtr = evenPtr.next;
            }
            // printList(headPtr);
        }
        
        return headPtr;
    }
    
    private void printList(ListNode head) {
        ListNode ptr = head;
        
        if (ptr != null) 
            System.out.printf("%d", ptr.val);
        
        ptr = ptr.next;
        while (ptr != null) {
            System.out.printf(", %d", ptr.val);
            ptr = ptr.next;
        }
        
        System.out.println();
    }
}