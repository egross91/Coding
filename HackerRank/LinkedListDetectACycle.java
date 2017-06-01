/*
Detect a cycle in a linked list. Note that the head pointer may be 'null' if the list is empty.

A Node is defined as: 
    class Node {
        int data;
        Node next;
    }
*/
// AC
boolean hasCycle(Node head) {
    if (head == null || head.next == null) { return false; }
    
    Node tortoise = head.next;
    Node hare = head.next.next;
    
    while (hare != null && tortoise != null && tortoise != hare) {
        tortoise = tortoise.next;
        
        if (hare.next != null) {
            hare = hare.next.next;
        }
        else {
            hare = hare.next;
        }
    }
    
    return (tortoise == hare);
}
