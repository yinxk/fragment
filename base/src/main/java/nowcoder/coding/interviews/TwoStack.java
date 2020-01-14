package nowcoder.coding.interviews;

import java.util.Random;
import java.util.Stack;

public class TwoStack {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public static void main(String[] args) {
        
        TwoStack twoStack = new TwoStack();
        Random random = new Random();
        int rPop = random.nextInt(5) + 1;
        for (int i = 0; i < random.nextInt(100); i++) {
            twoStack.push(i + 1);
            if (i % rPop == 0) {
                System.out.println(twoStack.pop());
            }
        }
    }
    
    public void push(int node) {
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }
    
    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        if (!stack2.empty()) {
            return stack2.pop();
        }
        return 0;
    }
    
    public void push1(int node) {
        stack1.push(node);
    }
    
    public int pop1() {
        if (stack1.empty() && stack2.empty()) {
            throw new RuntimeException("Queue is empty!");
        }
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
