package task4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {

    @Test
    public void pushTest() {
        Stack<Integer> s = new Stack<>();
        Assertions.assertEquals(0, s.size());

        s.push(1);
        Assertions.assertEquals(1, s.size());
        Assertions.assertEquals(1, s.peek());

        s.push(2);
        Assertions.assertEquals(2, s.size());
        Assertions.assertEquals(2, s.peek());
        s.push(3);
        Assertions.assertEquals(3, s.size());
        Assertions.assertEquals(3, s.peek());
        s.push(100);
        Assertions.assertEquals(4, s.size());
        Assertions.assertEquals(100, s.peek());
    }

    @Test
    public void popTest() {
        Stack<Integer> s = new Stack<>();
        Assertions.assertEquals(0, s.size());

        s.push(1);
        s.push(2);
        s.push(3);
        s.push(100);

        Assertions.assertEquals(100, s.peek());
        Assertions.assertEquals(100, s.pop());
        Assertions.assertEquals(3, s.size());


        Assertions.assertEquals(3, s.peek());
        Assertions.assertEquals(3, s.pop());
        Assertions.assertEquals(2, s.size());


        Assertions.assertEquals(2, s.peek());
        Assertions.assertEquals(2, s.pop());
        Assertions.assertEquals(1, s.size());


        Assertions.assertEquals(1, s.peek());
        Assertions.assertEquals(1, s.pop());
        Assertions.assertEquals(0, s.size());
    }

    @Test
    public void bracketTest() {
        Assertions.assertTrue(Stack.isCorrectBracketSequence("()()"));
        Assertions.assertTrue(Stack.isCorrectBracketSequence("(())()(())"));
        Assertions.assertTrue(Stack.isCorrectBracketSequence("(()((())()))"));
        Assertions.assertFalse(Stack.isCorrectBracketSequence("(()()(()"));
    }

}
