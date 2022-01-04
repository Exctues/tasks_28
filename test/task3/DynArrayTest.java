package task3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task3.DynArray;

public class DynArrayTest {
    @Test
    public void testMakeArray() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(0, a.count);
        a.makeArray(4);
        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(0, a.count);
    }

    @Test
    public void testAppend() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        a.append(0);
        a.append(10);
        a.append(20);

        Assertions.assertSame(0, a.getItem(0));
        Assertions.assertSame(10, a.getItem(1));
        Assertions.assertSame(20, a.getItem(2));

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(3, a.count);
    }

    @Test
    public void testInsertAtLeft() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        a.insert(10, 0);
        a.insert(20, 0);
        Assertions.assertSame(20, a.getItem(0));
        Assertions.assertSame(10, a.getItem(1));

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(2, a.count);
    }

    @Test
    public void testInsertAtRight() {
        DynArray<Integer> a = new DynArray<>(Integer.class);

        a.insert(10, 0);
        a.insert(20, 1);
        a.insert(30, 2);

        Assertions.assertSame(10, a.getItem(0));
        Assertions.assertSame(20, a.getItem(1));
        Assertions.assertSame(30, a.getItem(2));

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(3, a.count);
    }

    @Test
    public void testInsertInTheMiddle() {
        DynArray<Integer> a = new DynArray<>(Integer.class);

        a.insert(10, 0);
        a.insert(20, 1);
        a.insert(30, 2);

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(3, a.count);

        Assertions.assertSame(10, a.getItem(0));
        Assertions.assertSame(20, a.getItem(1));
        Assertions.assertSame(30, a.getItem(2));

        // from: 10  20 30
        //   to: 10 -10 20 30
        a.insert(-10, 1);

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(4, a.count);

        Assertions.assertSame(10, a.getItem(0));
        Assertions.assertSame(-10, a.getItem(1));
        Assertions.assertSame(20, a.getItem(2));
        Assertions.assertSame(30, a.getItem(3));
    }

    @Test
    public void testInsertInTheMiddleOverflow() {
        DynArray<Integer> a = new DynArray<>(Integer.class);

        for (int i = 0; i < 16; i++) {
            a.append(i);
            Assertions.assertSame(i, a.getItem(i));
        }

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(16, a.count);

        // from: 0 1 .. 15
        //   to: 0 1 .. 7 -10 8 .. 15
        a.insert(-10, 8);

        Assertions.assertSame(32, a.capacity);
        Assertions.assertSame(17, a.count);

        for (int i = 0; i < 17; i++) {
            if (i < 8) {
                Assertions.assertSame(i, a.getItem(i));
            } else if (i > 8) {
                Assertions.assertSame(i - 1, a.getItem(i));
            } else { // i == 8
                Assertions.assertSame(-10, a.getItem(i));
            }

        }
    }

    @Test
    public void testRemoveSingleElementInTheMiddle() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        for (int i = 0; i < 10; i++) {
            a.append(i);
            Assertions.assertSame(i, a.getItem(i));
        }

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(10, a.count);
        Assertions.assertSame(5, a.getItem(5));

        a.remove(5);

        Assertions.assertSame(16, a.capacity);
        Assertions.assertSame(9, a.count);
        Assertions.assertSame(6, a.getItem(5));
    }

    @Test
    public void testRemoveToShrinkFromLeft() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        for (int i = 0; i < 32; i++) {
            a.append(i);
            Assertions.assertSame(i, a.getItem(i));
        }

        Assertions.assertSame(32, a.capacity);
        Assertions.assertSame(32, a.count);

        // from:  0 .. 31
        //   to: 17 .. 31
        for (int i = 0; i < 17; i++) {
            a.remove(0);
            Assertions.assertSame(32 - i - 1, a.count);
            for (int j = 0; j < a.count; j++) {
                Assertions.assertSame(i + j + 1, a.getItem(j));
            }
        }

        Assertions.assertSame(21, a.capacity);
        Assertions.assertSame(15, a.count);
    }

    @Test
    public void testRemoveToShrinkFromMid() {
        DynArray<Integer> a = new DynArray<>(Integer.class);
        for (int i = 0; i < 32; i++) {
            a.append(i);
            Assertions.assertSame(i, a.getItem(i));
        }

        Assertions.assertSame(32, a.capacity);
        Assertions.assertSame(32, a.count);

        // from:  0 .. 31
        //   to: 0 .. 9 27 .. 31
        for (int i = 10; i < 27; i++) {
            a.remove(10);
            Assertions.assertSame(32 - i + 10 - 1, a.count);
            for (int j = 0; j < 10; j++) {
                Assertions.assertSame(j, a.getItem(j));
            }
            for (int j = 10; j < a.count; j++) {
                Assertions.assertSame((j - 10) + (i + 1), a.getItem(j));
            }
        }

        Assertions.assertSame(21, a.capacity);
        Assertions.assertSame(15, a.count);
    }
}
