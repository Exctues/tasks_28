package task3;

import java.lang.reflect.Array;
import java.util.*;

public class DynArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    @SuppressWarnings("rawtypes")
    Class clazz;

    final float expandCoefficient = 2.0f;
    final float shrinkCoefficient = 2 / 3f;

    @SuppressWarnings("rawtypes")
    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        if (new_capacity < 16)
            new_capacity = 16;

        //noinspection unchecked
        array = (T[]) Array.newInstance(this.clazz, new_capacity);
        capacity = new_capacity;
    }

    public T getItem(int index) {
        if (index < 0 || index >= count)
            throw new ArrayIndexOutOfBoundsException();

        return array[index];
    }

    public void append(T itm) {
        if (count == capacity) {
            T[] oldArray = array;
            makeArray((int) (capacity * expandCoefficient));
            System.arraycopy(oldArray, 0, array, 0, count);
        }

        array[count++] = itm;
    }

    public void insert(T itm, int index) {
        if (index < 0 || index > count)
            throw new ArrayIndexOutOfBoundsException();

        if (index == count) {
            append(itm);
            return;
        }

        T[] oldArray = array;
        if (count == capacity) {
            makeArray((int) (expandCoefficient * capacity));

            System.arraycopy(oldArray, 0, array, 0, index);
        }

        // shift elements after index by 1 step to right
        if (count - index >= 0) System.arraycopy(oldArray, index, array, index + 1, count - index);

        // insert
        array[index] = itm;

        count++;
    }

    public void remove(int index) {
        if (index < 0 || index >= count)
            throw new ArrayIndexOutOfBoundsException();

        T[] oldArray = array;
        if (index == count - 1) {
            array[index] = null;
            if ((count - 1) * 2 < capacity) {
                makeArray(Math.max((int) (capacity * shrinkCoefficient), 16));
                System.arraycopy(oldArray, 0, array, 0, index);
            }
            count--;
            return;
        }

        // If after remove we will have with less than 50% of buffer usage then shrink it
        if ((count - 1) * 2 < capacity) {
            makeArray(Math.max((int) (capacity * shrinkCoefficient), 16));
            System.arraycopy(oldArray, 0, array, 0, index);
        }

        System.arraycopy(oldArray, index + 1, array, index, count - 1 - index);

        count--;
    }

}
