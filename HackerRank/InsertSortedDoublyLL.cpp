/*  
   Insert Node in a doubly sorted linked list 
   After each insertion, the list should be sorted
   Node is defined as
   struct Node
   {
     int data;
     Node *next;
     Node *prev
   }
*/
Node* SortedInsert(Node *head,int data)
{
    if (!head) {
        head = new Node();
		head->data = data;
		head->prev = NULL;
		head->next = NULL;
        return head;
	}
    else if (head->data > data) {
        Node* ti = new Node();
		ti->data = data;
		ti->next = head;
		ti->prev = NULL;
		head->prev = ti;
		
		return ti;
    }
	else {
        Node* curr = head;
		// Find the position to place the Node
		while (curr->data < data && curr->next)
			curr = curr->next;
        
        if (!curr->next && curr->data < data) {
			Node* ti = new Node();
			curr->next = ti;
			
			ti->data = data;
			ti->prev = curr;
			ti->next = NULL;
        }
		else {
			curr = curr->prev;
			
			Node* save = curr->next;
			// Link the new Node
			Node* ti = new Node();
			ti->data = data;
			ti->next = save;
			ti->prev = curr;
			
			// Keep the List Linked
			curr->next = ti;
			save->prev = ti;
		}
			return head;
	}
}