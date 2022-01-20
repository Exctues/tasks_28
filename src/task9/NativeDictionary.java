package task9;

import java.lang.reflect.Array;

class NativeDictionary<T> {
    public int size;
    public String[] slots;
    public T[] values;
    public static int step = 1;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        return Math.abs(key.hashCode() % slots.length);
    }

    public boolean isKey(String key) {
        return get(key) != null;
    }

    public void put(String key, T value) {
        // гарантированно записываем
        // значение value по ключу key
        int h1 = hashFun(key);
        for (int i = 0; i <= size; i++) {
            int idx = (h1 + step * i) % size;
            if (slots[idx] == null || slots[idx].equals(key)) {
                slots[idx] = key;
                values[idx] = value;
                break;
            }
        }

    }

    public T get(String key) {
        int h1 = hashFun(key);
        for (int i = 0; i <= size; i++) {
            int idx = (h1 + step * i) % size;
            if (slots[idx] != null && slots[idx].equals(key))
                return values[idx];
        }
        return null;
    }

}