package task18;

import java.util.*;

class Heap {
    // хранит неотрицательные числа-ключи
    public int[] HeapArray;
    public int curSize = 0;

    //    public int depth = 0;
    public Heap() {
        HeapArray = null;
    }

    public void MakeHeap(int[] a, int depth) {
//        this.depth = depth;
        int maxLen = (int) Math.pow(2, depth + 1) - 1;
        HeapArray = new int[maxLen];
        Arrays.fill(HeapArray, -1);
        for (int x : a) {
            Add(x);
            System.out.println(Arrays.toString(HeapArray));
        }
    }

    public void swap(int[] a, int p1, int p2) {
        int tmp = a[p1];
        a[p1] = a[p2];
        a[p2] = tmp;
    }

    public int GetMax() {
        if (curSize == 0)
            return -1;
        int val = HeapArray[0];
        if (curSize > 0) {
            int currentPos = 0;
            HeapArray[currentPos] = HeapArray[curSize - 1];
            HeapArray[curSize - 1] = -1;
            while (currentPos * 2 + 1 < HeapArray.length) {
                // there are still children below
                // swap with the biggest children
                // if it is also bigger that we are.
                int leftPos = currentPos * 2 + 1;
                int rightPos = currentPos * 2 + 2;
                int maxi = Math.max(HeapArray[leftPos], HeapArray[rightPos]);
                if (HeapArray[currentPos] >= maxi)
                    break;

                if (HeapArray[leftPos] == maxi) {
                    swap(HeapArray, currentPos, leftPos);
                    currentPos = leftPos;
                } else {
                    swap(HeapArray, currentPos, rightPos);
                    currentPos = rightPos;
                }

            }
        }
        --curSize;
        return val;
    }

    public boolean Add(int key) {
        if (curSize == HeapArray.length)
            return false;

        HeapArray[curSize] = key;
        int currentPos = curSize;
        while (currentPos > 0) {
            int parentPos = (curSize - 1) / 2;
            if (HeapArray[parentPos] > HeapArray[currentPos])
                break;
            swap(HeapArray, parentPos, currentPos);
            currentPos = parentPos;
        }

        ++curSize;
        return true;
    }

}