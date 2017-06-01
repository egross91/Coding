import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
// AC
public class Solution {
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<Integer>();

        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();

        for (int i = 0; i < n; i++) {
            int operation = scan.nextInt();
            if (operation == 1) { // enqueue
              queue.enqueue(scan.nextInt());
            } else if (operation == 2) { // dequeue
              queue.dequeue();
            } else if (operation == 3) { // print/peek
              System.out.println(queue.peek());
            }
        }
        scan.close();
    }
    
    private static class MyQueue<T> {
        private Stack<T> in;
        private Stack<T> out;
        
        public MyQueue() {
            this.in = new Stack<T>();
            this.out = new Stack<T>();
        }
        
        public void enqueue(T element) {
            this.in.push(element);
        }
        
        public T dequeue() {
            if (this.out.empty()) {
                this.fill();
            }
            
            return this.out.pop();
        }
        
        public T peek() {
            if (this.out.empty()) {
                this.fill();
            }
            
            return this.out.peek();
        }
        
        private void fill() {
            while (!this.in.empty()) {
                this.out.push(this.in.pop());
            }
        }
    }
}
