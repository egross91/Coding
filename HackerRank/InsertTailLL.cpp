/*
  Insert Node at the end of a linked list 
  head pointer input could be NULL as well for empty list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Insert(Node *head,int data)
{
    // Point to head
    Node* headPtr = head;
    
    // Make the Tail node
    Node* tail = new Node();
    tail->data = data;
    tail->next = NULL;
    
    if (head == NULL) // EMPTY CASE
        headPtr = tail;
    else { // NON-EMPTY CASE
        while (head->next != NULL)
            head = head->next;
        head->next = tail;
    }
    
    return headPtr;
}