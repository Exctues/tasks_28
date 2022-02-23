package task16;

import java.util.Arrays;

public class AlgorithmsDataStructures2 {

    public static void helper(int[] a, int[] b, int idx, int l, int r) {
        if (l >= r || idx >= a.length)
            return;
        int midIdx = (l + r) / 2;
        b[idx] = a[midIdx];
        helper(a, b, idx * 2 + 1, l, midIdx);  // left child
        helper(a, b, idx * 2 + 2, midIdx, r);  // right child
    }

    public static int[] GenerateBBSTArray(int[] a) {
        Arrays.sort(a);
        int[] b = new int[a.length];
        helper(a, b, 0, 0, b.length);
        return b;
    }

}
