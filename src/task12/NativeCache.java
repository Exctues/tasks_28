package task12;

import java.lang.reflect.Array;

/**
 * @param <T>
 */
class NativeCache<T> {
    public int size;
    public String[] slots;
    public T[] values;
    public int[] hits;

    NativeCache(int size_, Class<T> clazz) {
        size = size_;
        slots = new String[size];
        hits = new int[size];
        @SuppressWarnings("unchecked") final T[] a = (T[]) Array.newInstance(clazz, size);
        values = a;
    }

    public int hash(String key) {
        return key.hashCode() % size;
    }

    private int getIdxOfKey(String key) {
        int h1 = hash(key);
        for (int i = 0; i < size; i++) {
            int idx = (h1 + i) % size;
            if (slots[idx] != null && slots[idx].equals(key)) {
                return idx;
            }
        }
        return -1;
    }

    public T get(String key) {
        int idx = getIdxOfKey(key);
        if (idx == -1)
            return null;

        hits[idx]++;
        return values[idx];
    }

    public int getIdxOfLeastPopularKey() {
        int lowestHitsIdx = 0;
        int lowestHits = hits[0];
        for (int i = 1; i < size; i++) {
            if (hits[i] < lowestHits) {
                lowestHits = hits[i];
                lowestHitsIdx = i;
            }
        }
        return lowestHitsIdx;
    }

    public void set(String key, T value) {
        // наивная проверка за O(n), что такого ключа не существует,
        // иначе при удаление другого элемнта схожего по хешу,
        // мы можем добавить два одинаковых ключа, что не допустимо в кеше (и хеш-таблице).
        int idxOfSameElement = getIdxOfKey(key);

        if(idxOfSameElement == -1){
            // удаляем самый непопулярный элемент
            int lowestHitsIdx =  getIdxOfLeastPopularKey();
            slots[lowestHitsIdx] = null;
        }
        else {
            // Удаляем ключ, т.к. мы уже имеем такой в кеше.
            assert slots[idxOfSameElement].equals(key);
            slots[idxOfSameElement] = null;
        }

        int h1 = hash(key);
        for (int i = 0; i < size; i++) {
            int idx = (h1 + i) % size;
            if (slots[idx] == null) {
                slots[idx] = key;
                values[idx] = value;
                hits[idx] = 1;
                break;
            }
        }
    }
}