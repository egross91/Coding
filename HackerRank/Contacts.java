import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Try trie = new Try();
        
        int n = in.nextInt();
        
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            
            if (op.equals("add")) {
                trie.add(contact);
            } else {
                System.out.println(trie.getPartialCount(contact));
            }
        }
    }
    
    private static class Try {
        private Node root;
        
        private class Node {
            private Map<Character, Node> map;
            private int wordCount;
            
            private Node() {
                this(0);
            }
            
            private Node(int count) {
                this.map       = new HashMap<Character, Node>();
                this.wordCount = count;
            }
            
            private void addNode(char c, Node n) {
                this.map.put(c, n);
            }
            
            private Node getNode(char c) {
                return this.map.get(c);
            }
            
            private void incrementWordCount() {
                this.wordCount++;
            }
            
            private int getWordCount() {
                return this.wordCount;
            }
        }
        
        public Try() {
            this.root = new Node();
        }
        
        public void add(String word) {
            Node current = this.root;
            
            for (int i = 0; i < word.length(); ++i) {
                char c    = word.charAt(i);
                Node next = current.getNode(c);
                current.incrementWordCount();
                
                if (next == null) { // Case of hitting a new node.
                    if (i == word.length()-1) {
                        // Pass 1 because we will _not_ be incrementing the count on the iteration of the loop.
                        current.addNode(c, new Node(1));
                    } else {
                        // Pass 0 because we will be incrementing the count on the iteration of the loop.
                        current.addNode(c, new Node(0)); 
                    }
                    
                    // Traverse.
                    current = current.getNode(c);
                } else {
                    // Traverse.
                    current = next;
                }
            }
        }
        
        public int getPartialCount(String partial) {
            Node current = this.root;
            
            for (char c : partial.toCharArray()) {
                current = current.getNode(c);
                
                // If the prefix does not exist, we're done.
                if (current == null) {
                    return 0;
                }
            }
            
            return current.getWordCount();
        }
    }
}
