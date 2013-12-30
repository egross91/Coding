/*
   Find merge point of two linked lists
   Node is defined as
   struct Node
   {
       int data;
       Node* next;
   }
*/
int FindMergeNode(Node *headA, Node *headB)
{
	if (headA->next == headB->next)
		return headA->next->data;
		
	while (headA->next && headB->next) {
		if (headA->next == headB->next)
			return headA->next->data;
		else if (headA->next->next == headB->next)
			return headA->next->next->data;
		else if (headA->next == headB->next->next)
			return headA->next->data;
		
		headA = headA->next;
		headB = headB->next;
	}
    return headA->data;
}