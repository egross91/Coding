/*
  Merge two sorted lists A and B as one linked list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* MergeLists(Node *headA, Node* headB)
{
	if (!headA)
		return headB;
	if (!headB)
		return headA;
		
	Node* head = NULL;
	if (headA->data < headB->data)
		head = headA;
	else {
		head = headB;
		headB = headA;
		headA = head;
	}
	
	while (headA->next && headB) {
		if (headA->next->data < headB->data) 
			headA = headA->next;
		
		else {
			Node* tmp = headA->next;
			headA->next = headB;
			headB = tmp;
		}
		
	}
	if (!headA->next)
		headA->next = headB;
	
	return head;
}