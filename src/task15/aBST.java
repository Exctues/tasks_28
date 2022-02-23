package task15;

import java.util.*;

class aBST {
    public Integer Tree[]; // массив ключей

    public aBST(int depth) {
        // правильно рассчитайте размер массива для дерева глубины depth:
        int tree_size = (int) Math.pow(2, depth + 1) - 1;
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++)
            Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        int curIdx = 0;
        while (curIdx < Tree.length) {
            if (Tree[curIdx] == null)
                return -curIdx;
            if (Tree[curIdx] == key)
                return curIdx;
            curIdx = Tree[curIdx] > key ? 2 * curIdx + 1 : 2 * curIdx + 2;
        }
        return null;
    }

    public int AddKey(int key) {
        Integer slot = FindKeyIndex(key);
        if (slot == null)
            return -1;

        if (slot >= 0) {
            if (slot == 0 && Tree[slot] == null)
                Tree[slot] = key;
            return slot;
        }

        int idx = -slot;
        Tree[idx] = key;
        return idx;
    }

}
