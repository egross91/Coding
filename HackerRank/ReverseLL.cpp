/*
  Reverse a linked list and return pointer to the head
  The input list will have at least one element  
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Reverse(Node *head)
{
    if (head) {
        Node* prev = NULL;
        Node* fore = NULL;
        Node* curr = head;
        while (curr) {
            fore = curr->next;
            curr->next = prev;
            prev = curr;
            curr = fore;
        }
        return prev;
    }
    else
        return head;
    
}