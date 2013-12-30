/*
  Remove all duplicate elements from a sorted linked list
  Node is defined as 
  struct Node
  {
     int data;
     struct Node *next;
  }
*/
Node* RemoveDuplicates(Node *head)
{
	Node* headPtr = head;
	while (head->next) {
		int val = head->data;
        Node* here = head;
		Node* chkr = head->next;
		while (chkr->data == val && chkr->next) {
			Node* save = chkr->next;
			delete chkr;
			chkr = save;
		}
		if (here->data != chkr->data) {
			here->next = chkr;
			head = chkr;
			continue;
		}
		delete chkr;
		here->next = NULL;
    }
	return headPtr;
}