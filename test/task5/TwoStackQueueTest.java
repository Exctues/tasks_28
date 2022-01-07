package task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoStackQueueTest {
    @Test
    public void SimpleTest(){
        TwoStackQueue<Integer> s = new TwoStackQueue<>();
        s.enqueue(1);
        s.enqueue(2);
        s.enqueue(3);

        Assertions.assertEquals(1,s.dequeue());
        Assertions.assertEquals(2,s.dequeue());
        Assertions.assertEquals(3,s.dequeue());

        Assertions.assertEquals(0, s.size());
    }
}
