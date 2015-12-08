class MyQueue {
    private Stack<Integer> firstStack  = new Stack<Integer>();
    private Stack<Integer> secondStack = new Stack<Integer>();
    
    // Push element x to the back of queue.
    public void push(int x) {
        firstStack.push(x);
    }

    // Removes the element from in front of queue.
    public void pop() {
        if (secondStack.empty()) 
            moveFromStackToStack(firstStack, secondStack);
        
        if (!secondStack.empty())
            secondStack.pop();
    }

    // Get the front element.
    public int peek() {
        if (secondStack.empty()) 
            moveFromStackToStack(firstStack, secondStack);
        
        if (!secondStack.empty())
            return secondStack.peek();
        
        return -1;
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return (firstStack.empty() && secondStack.empty());
    }
    
    private void moveFromStackToStack(Stack<Integer> from, Stack<Integer> to) {
        while (!from.empty()) 
            to.push(from.pop());
    }
}