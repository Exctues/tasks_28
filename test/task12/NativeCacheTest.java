package task12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NativeCacheTest {
    @Test
    public void test1() {
        NativeCache<Integer> cache = new NativeCache<>(3, Integer.class);
        cache.set("1", 1);
        cache.set("2", 2);
        cache.set("3", 3);

        // make sure elements were correctly inserted.
        Assertions.assertNull(cache.get("0"));
        Assertions.assertEquals(1, cache.get("1"));
        Assertions.assertEquals(2, cache.get("2"));
        Assertions.assertEquals(3, cache.get("3"));
        Assertions.assertNull(cache.get("4"));

        // make sure we correctly find the "the least asked key"
        cache.get("1");
        cache.get("3");
        Assertions.assertEquals("2", cache.slots[cache.getIdxOfLeastPopularKey()]);
        cache.get("2");

        cache.get("1");
        cache.get("2");
        Assertions.assertEquals("3", cache.slots[cache.getIdxOfLeastPopularKey()]);
        cache.get("3");

        cache.get("3");
        cache.get("2");
        Assertions.assertEquals("1", cache.slots[cache.getIdxOfLeastPopularKey()]);
        cache.get("1");

        // correctly reset single element
        cache.set("1", 1);
        Assertions.assertEquals("1", cache.slots[cache.getIdxOfLeastPopularKey()]);

        // reset other elements
        cache.set("2", 2);
        cache.set("3", 3);
        Assertions.assertEquals(1, cache.hits[0]);
        Assertions.assertEquals(1, cache.hits[1]);
        Assertions.assertEquals(1, cache.hits[2]);
    }
}
