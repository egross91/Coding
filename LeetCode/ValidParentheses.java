public class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        
        try {
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                
                if (c == '(' || c == '{' || c == '[') {
                    stack.add(c);
                }
                else {
                    char top = stack.peek();
                    
                    if (top == '(' && c != ')') 
                        return false;
                    else if (top == '{' && c != '}') 
                        return false;
                    else if (top == '[' && c != ']')
                        return false;
                    
                    stack.pop();
                }
            }
        } catch (Exception e) {
            return false;
        }
        
        return (stack.isEmpty());
    }
}