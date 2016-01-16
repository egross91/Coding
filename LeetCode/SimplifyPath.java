public class Solution {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0)
            return null;
        
        Stack<String> stack = new Stack<String>();
        String[] data = path.split("/");
        
        for (int i = 0; i < data.length; ++i) {
            if (data[i].equals("..")) {
                if (!stack.empty())
                    stack.pop();
            }
            else if (!data[i].equals(".") && (data[i].length() > 0)) 
                stack.push(data[i]);
        }
        
        return stringify(stack);
    }
    
    private String stringify(Stack<String> s) {
        Stack<String> s2 = new Stack<String>();
        
        while (!s.empty())
            s2.push(s.pop());
        
        StringBuilder builder = new StringBuilder();
        
        while (true) {
            builder.append("/");
            
            if (!s2.empty())
                builder.append(s2.pop());
            if (s2.empty())
                break;
        }
        
        return builder.toString();
    }
}