package task18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class HeapTest {
    @Test
    public void addTest() {
        //       7
        //    5      6
        //  1   2  3   4
        int[] a = {5, 6, 7, 1, 2, 3, 4,};
        Heap h = new Heap();
        h.MakeHeap(a, 2);
        System.out.println(Arrays.toString(h.HeapArray));
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++) {
            int res = h.GetMax();
            Assertions.assertEquals(a[a.length - i - 1], res);
        }
    }

    @Test
    public void makeHeapTest2() {
        int[] a = {};
        Heap h = new Heap();
        h.MakeHeap(a, 2);

        Assertions.assertTrue(h.Add(5));
        System.out.println(Arrays.toString(h.HeapArray));
        Assertions.assertTrue(h.Add(10));
        System.out.println(Arrays.toString(h.HeapArray));
        Assertions.assertTrue(h.Add(6));
        System.out.println(Arrays.toString(h.HeapArray));
        Assertions.assertTrue(h.Add(7));
        System.out.println(Arrays.toString(h.HeapArray));
        Assertions.assertTrue(h.Add(10));
        System.out.println(Arrays.toString(h.HeapArray));
        Assertions.assertTrue(h.Add(6));
        System.out.println(Arrays.toString(h.HeapArray));
    }
}
