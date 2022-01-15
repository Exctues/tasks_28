package task8;

public class HashTable {
    public int size;
    public int step;
    public String[] slots;

    public HashTable(int sz, int stp) {
        size = sz;
        step = stp;
        slots = new String[size];
        for (int i = 0; i < size; i++)
            slots[i] = null;
    }

    public int hashFun(String value) {
        return Math.abs(value.hashCode() % slots.length);
    }

    public int seekSlot(String value) {
        int h1 = hashFun(value);
        for (int i = 0; i <= size; i++) {
            int idx = (h1  + step * i) % size;
            if (slots[idx] == null)
                return idx;
        }

        return -1;
    }

    public int put(String value) {
        int slot = seekSlot(value);
        if (slot == -1)
            return -1;

        slots[slot] = value;
        return slot;
    }

    public int find(String value) {
        int h1 = hashFun(value);
        for (int i = 0; i <= size; i++) {
            int idx = (h1  + step * i) % size;
            if (slots[idx].equals(value))
                return idx;
        }
        return -1;
    }
}
