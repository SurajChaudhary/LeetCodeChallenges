package challenges;

import java.util.Stack;

public class ImplementQueueUsingStacksChallenge {
    public static void main(String[] args) {
        int x = 1;
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_3 = obj.peek();
        System.out.println("Element on top : " + param_3);
        System.out.println(obj.pop());
        System.out.println("Is Empty : " + obj.empty());

    }
}

class MyQueue {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    int curr_size;

    /** Initialize your data structure here. */
    public MyQueue() {
        curr_size = 0;
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(s1.isEmpty() && s2.isEmpty()) {
            s1.push(x);
        }else {
            while(!s2.isEmpty()) {
                int curr = s2.pop();
                s1.push(curr);
            }
            s1.push(x);
        }
        while(!s1.isEmpty()) {
            curr_size++;
            int curr = s1.pop();
            s2.push(curr);
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(!s2.isEmpty()) {
            curr_size--;
            return s2.pop();
        }else {
            return -1;
        }
    }

    /** Get the front element. */
    public int peek() {
        if(!s2.isEmpty()) {
            return s2.peek();
        }else {
            return -1;
        }
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s2.isEmpty();
    }
}

