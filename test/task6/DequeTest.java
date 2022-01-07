package task6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DequeTest {

    @Test
    public void addFrontTest() {
        Deque<Integer> deq = new Deque<>();
        Assertions.assertNull(deq.removeFront());
        Assertions.assertNull(deq.removeTail());


        // TC 1
        deq.addFront(1);
        deq.addFront(2);
        deq.addFront(3);
        Assertions.assertEquals(3, deq.size());

        Assertions.assertEquals(3, deq.removeFront());
        Assertions.assertEquals(2, deq.removeFront());
        Assertions.assertEquals(1, deq.removeFront());
        Assertions.assertEquals(0, deq.size());

        // TC 2, remove from tail
        deq.addFront(1);
        deq.addFront(2);
        deq.addFront(3);
        Assertions.assertEquals(3, deq.size());

        Assertions.assertEquals(1, deq.removeTail());
        Assertions.assertEquals(2, deq.removeTail());
        Assertions.assertEquals(3, deq.removeTail());
        Assertions.assertEquals(0, deq.size());
    }

    @Test
    public void isPalindromeTest() {
        Assertions.assertTrue(Deque.isPalindrome("123454321"));
        Assertions.assertFalse(Deque.isPalindrome("123456321"));
        Assertions.assertTrue(Deque.isPalindrome("11"));
        Assertions.assertFalse(Deque.isPalindrome("112"));
    }
}
