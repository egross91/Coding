import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static boolean isBalanced(String expression) {
        if (expression == null) { return false; }
        
        Stack<Character> leftBrackets = new Stack<Character>();
        
        for (int i = 0; i < expression.length(); ++i) {
            char character = expression.charAt(i);
            try {
                switch (character) {
                    case '(':
                    case '[':
                    case '{':
                        leftBrackets.push(character); break;
                    case ')':
                        if (leftBrackets.peek() != '(') { return false; }

                        leftBrackets.pop();
                        break;
                    case ']':
                        if (leftBrackets.peek() != '[') { return false; }

                        leftBrackets.pop();
                        break;
                    case '}':
                        if (leftBrackets.peek() != '{') { return false; }

                        leftBrackets.pop();
                        break;
                }
            } catch (Exception e) {
                return false;
            }
        }
        
        return leftBrackets.size() == 0;
    }
  
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int a0 = 0; a0 < t; a0++) {
            String expression = in.next();
            System.out.println( (isBalanced(expression)) ? "YES" : "NO" );
        }
    }
}
