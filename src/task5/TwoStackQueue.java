package task5;

import java.util.*;

public class TwoStackQueue<T> {
    private final Stack<T> s1;
    private final Stack<T> s2;

    public TwoStackQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    public void enqueue(T item) {
        s1.push(item);
    }

    public T dequeue() {
        if(s2.empty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }
        }

        if(!s2.empty())
            return s2.pop();

        return null;
    }

    public int size() {
        return s1.size() + s2.size();
    }
}
