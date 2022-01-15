package task8;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class HasTableTest {
    @Test
    public void testAddOverLimit(){
        final int size = 19;
        final int step = 3;
        HashTable a = new HashTable(size,step);

        for (int i = 0; i < size; i++) {
            Assertions.assertNotEquals(-1, a.put("abc"));
        }

        Assertions.assertEquals(-1, a.put("abc"));
    }

    @Test
    public void testAddOverLimitDifferent(){
        final int size = 19;
        final int step = 3;
        HashTable a = new HashTable(size,step);

        for (int i = 0; i < size; i++) {
            Assertions.assertNotEquals(-1, a.put("abc" + i));
        }

        Assertions.assertEquals(-1, a.put("abc"));
    }

    @Test
    public void testFind(){
        final int size = 19;
        final int step = 3;
        HashTable a = new HashTable(size,step);

        for (int i = 0; i < size; i++) {
            Assertions.assertNotEquals(-1, a.put("abc" + i));
        }

        for (int i = 0; i < size; i++) {
            Assertions.assertNotEquals(-1, a.find("abc" + i));
        }
    }
}
