class MinStack {
    private Stack<MinIntPair> minStack = new Stack<MinIntPair>();
    
    public void push(int x) {
        if (x < getMin()) 
            minStack.push(new MinIntPair(x,x));
        else 
            minStack.push(new MinIntPair(x,getMin()));
    }

    public void pop() {
        if (!minStack.empty())
            minStack.pop();
    }

    public int top() {
        if (minStack.empty())
            return -1;
        
        return minStack.peek().value;
    }

    public int getMin() {
        if (minStack.empty())
            return Integer.MAX_VALUE;
        
        return minStack.peek().stackMin;
    }
    
    private class MinIntPair {
        public final int value;
        public final int stackMin;
        
        public MinIntPair(int v, int s) {
            this.value = v;
            this.stackMin = s;
        }
    }
}
