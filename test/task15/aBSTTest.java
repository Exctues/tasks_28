package task15;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class aBSTTest {
    @Test
    public void testAddKeyDepth0() {
        aBST t = new aBST(0);
        Assertions.assertEquals(0, t.AddKey(100));
        // add same
        Assertions.assertEquals(0, t.AddKey(100));
        // fail add righter
        Assertions.assertEquals(-1, t.AddKey(101));
        // fail add lefter
        Assertions.assertEquals(-1, t.AddKey(99));
    }

    @Test
    //           100
    //       50       150
    //     25  75   125  175
    //                     200 -- fail
    public void testAddKeyDepth2() {
        aBST t = new aBST(2);
        Assertions.assertEquals(0, t.AddKey(100));
        Assertions.assertEquals(1, t.AddKey(50));
        Assertions.assertEquals(2, t.AddKey(150));
        Assertions.assertEquals(3, t.AddKey(25));
        Assertions.assertEquals(4, t.AddKey(75));
        Assertions.assertEquals(5, t.AddKey(125));
        Assertions.assertEquals(6, t.AddKey(175));
        Assertions.assertEquals(-1, t.AddKey(200)); // fail
    }
}
