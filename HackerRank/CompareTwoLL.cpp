/*
  Compare two linked lists A and B
  Return 1 if they are identical and 0 if they are not. 
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
int CompareLists(Node *headA, Node* headB)
{
	if ((!headA && headB) || (headA && !headB))
		return 0;
	else {
		while (headA && headB) {
			if (headA->data == headB->data) {
				headA = headA->next;
				headB = headB->next;
				continue;
			}
			return 0;
		}
		if ((!headA && headB) || (headA && !headB))
			return 0;
		return 1;
	}
}