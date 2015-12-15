class MyStack {
    private Queue<Integer> inQueue  = new LinkedList<Integer>();
    private Queue<Integer> outQueue = new LinkedList<Integer>();
    
    // Push element x onto stack.
    public void push(int x) {
        inQueue.add(x);
    }

    // Removes the element on top of the stack.
    public void pop() {
        // Merge.
        moveNElementsFromQueueToQueue(inQueue, outQueue, 0);
        // Pick out last inserted element.
        moveNElementsFromQueueToQueue(outQueue, inQueue, 1);
        
        // if (outQueue.isEmpty())
        //     throw new OutOfBoundsException("No more elements within MyStack.");
        
        outQueue.remove();
    }

    // Get the top element.
    public int top() {
        // Merge.
        moveNElementsFromQueueToQueue(inQueue, outQueue, 0);
        // Pick out last inserted element.
        moveNElementsFromQueueToQueue(outQueue, inQueue, 1);
        
        // if (outQueue.isEmpty())
        //     throw new OutOfBoundsException("No more elements within MyStack.");
        
        int value = outQueue.peek();
        moveNElementsFromQueueToQueue(outQueue, inQueue, 0);
        
        return value;
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return (inQueue.isEmpty() && outQueue.isEmpty());
    }
    
    private void moveNElementsFromQueueToQueue(Queue<Integer> from, Queue<Integer> to, int leave) {
        while (!from.isEmpty() && from.size() > leave)
            to.add(from.remove());
    }
}