package task4;

import java.util.*;

public class Stack<T> {
    private final Deque<T> a;

    public Stack() {
        a = new ArrayDeque<>();
    }

    public int size() {
        return a.size();
    }

    public T pop() {
        return a.pollFirst();
    }

    public void push(T val) {
        a.offerFirst(val);
    }

    public T peek() {
        return a.peekFirst();
    }

    public static boolean isCorrectBracketSequence(String str) {
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char x = str.charAt(i);
            if (s.size() != 0 && s.peek() == '(' && x == ')')
                s.pop();
            else
                s.push(x);
        }
        return s.size() == 0;
    }
}