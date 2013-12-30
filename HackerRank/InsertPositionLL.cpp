/*
  Insert Node at a given position in a linked list 
  The linked list will not be empty and position will always be valid
  First element in the linked list is at position 0
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* InsertNth(Node *head, int data, int position)
{
	Node* headPtr = head;
  
	// Create Node that will be inserted
	Node* toInsert = new Node();
	toInsert->data = data;
 
	if (position == 0) {
		toInsert->next = head;
		headPtr = toInsert;
	} 
	else {
		while (position-- != 1) 
			head = head->next;
      
		toInsert->next = head->next;
		head->next = toInsert;
	}
      
	return headPtr;
}