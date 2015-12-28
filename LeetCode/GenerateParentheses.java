public class Solution {
    public List<String> generateParenthesis(int n) {
        Set<String> parentheses = new HashSet<String>();
        generateParentheses(0, 0, n, "", parentheses);
        
        return new LinkedList<String>(parentheses);
    }
    
    private void generateParentheses(int lparen, int rparen, int n,                                   String str, Set<String> parens) {
        if (lparen == n && rparen == n)
            parens.add(str);
        else if (lparen <= n && rparen <= n) {
            String newStr;
            
            if (lparen < n) {
                newStr = str + "(";
                
                generateParentheses(lparen+1, rparen, n, newStr, parens);
                generateParentheses(lparen+1, rparen+1, n, newStr+")", parens);
            }
            if (rparen < n && lparen > rparen) {
                newStr = str + ")";
                
                generateParentheses(lparen, rparen+1, n, newStr, parens);
            }
        }
    }
}