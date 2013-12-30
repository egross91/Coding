/*
  Detect loop in a linked list 
  List could be empty also
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
int HasCycle(Node* head)
{
    if (!head)
        return 0;
    
    int i = 100;
    while (i-- && head)
        head = head->next;
    if (!head)
        return 0;
    return 1;
}