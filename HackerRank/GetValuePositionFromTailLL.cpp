/*
  Get Nth element from the end in a linked list of integers
  Number of elements in the list will always be greater than N.
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
int GetNode(Node *head,int positionFromTail)
{   
	Node* back = head;
    while (positionFromTail--)
        head = head->next;
    
    while (head->next) {
        head = head->next;
        back = back->next;
    }
    
    return back->data;
}