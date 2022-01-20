package task9;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NativeDictionaryTest {

    @Test
    public void testPut() {
        NativeDictionary<Integer> a = new NativeDictionary<>(100, Integer.class);

        Assertions.assertFalse(a.isKey("123"));
        Assertions.assertFalse(a.isKey("12345"));

        a.put("123", 5);
        Assertions.assertEquals(5, a.get("123"));
        a.put("123", 50);
        Assertions.assertEquals(50, a.get("123"));

        a.put("12345", 10);
        Assertions.assertEquals(10, a.get("12345"));
        a.put("12345", 100);
        Assertions.assertEquals(100, a.get("12345"));

        Assertions.assertTrue(a.isKey("123"));
        Assertions.assertTrue(a.isKey("12345"));

        for (int i = 0; i < 20; i++) {
            Assertions.assertNull(a.get("" + i));
            Assertions.assertFalse(a.isKey("" + i));
        }
    }

    @Test
    public void testGetEmpty() {
        NativeDictionary<Integer> a = new NativeDictionary<>(100, Integer.class);

        for (int i = 0; i < 100; i++) {
            Assertions.assertNull(a.get("" + i));
        }
    }
}

