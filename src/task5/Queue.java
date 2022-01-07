package task5;

import java.util.*;

public class Queue<T> {
    private final Deque<T> deque;

    public Queue() {
        deque = new ArrayDeque<>();
    }

    // O(1)
    public void enqueue(T item) {
        deque.offerFirst(item);
    }

    // O(1)
    public T dequeue() {
        return deque.pollLast();
    }

    public int size() {
        return deque.size();
    }


    public void rotateByN(int n){
        while(n > 0){
            enqueue(dequeue());
            n--;
        }
    }

}