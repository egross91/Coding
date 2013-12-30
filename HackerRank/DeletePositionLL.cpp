/*
  Delete Node at a given position in a linked list 
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* Delete(Node *head, int position)
{
	Node* headPtr = head;
  
	Node* save = NULL;
	if (position == 0) {
		save = head->next;
		delete head;
		headPtr = save;
	}
	else {
		while (position-- != 1)
			head = head->next;
		save = head->next->next;
		delete head->next;
		head->next = save;
	}
	return headPtr;
}