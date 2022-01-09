package task7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class OrderedListTest {
    @Test
    public void constructionTest() {
        OrderedList<Integer> list = new OrderedList<>(true);
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
        Assertions.assertEquals(0, list.count());
    }

    @Test
    public void addAscendingTest() {
        OrderedList<Integer> list = new OrderedList<>(true);

        list.add(2);
        list.add(1);
        list.add(3);
        Assertions.assertEquals(3, list.count());
        Assertions.assertEquals(1, list.head.value);
        Assertions.assertEquals(3, list.tail.value);


        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(3, a.size());
        Assertions.assertEquals(1, a.get(0).value);
        Assertions.assertEquals(2, a.get(1).value);
        Assertions.assertEquals(3, a.get(2).value);
    }

    @Test
    public void addDescendingTest() {
        OrderedList<Integer> list = new OrderedList<>(false);

        list.add(2);
        list.add(1);
        list.add(3);
        Assertions.assertEquals(3, list.count());
        Assertions.assertEquals(3, list.head.value);
        Assertions.assertEquals(1, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(3, a.size());
        Assertions.assertEquals(3, a.get(0).value);
        Assertions.assertEquals(2, a.get(1).value);
        Assertions.assertEquals(1, a.get(2).value);
    }

    @Test
    public void deleteHeadAscTest() {
        OrderedList<Integer> list = new OrderedList<>(true);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(1);
        Assertions.assertEquals(2, list.head.value);
        Assertions.assertEquals(3, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(2, a.get(0).value);
        Assertions.assertEquals(3, a.get(1).value);
    }

    @Test
    public void deleteMidAscTest() {
        OrderedList<Integer> list = new OrderedList<>(true);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(2);
        Assertions.assertEquals(1, list.head.value);
        Assertions.assertEquals(3, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(1, a.get(0).value);
        Assertions.assertEquals(3, a.get(1).value);
    }

    @Test
    public void deleteTailAscTest() {
        OrderedList<Integer> list = new OrderedList<>(true);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(3);
        Assertions.assertEquals(1, list.head.value);
        Assertions.assertEquals(2, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(1, a.get(0).value);
        Assertions.assertEquals(2, a.get(1).value);
    }

    @Test
    public void deleteHeadDesTest() {
        OrderedList<Integer> list = new OrderedList<>(false);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(1);
        Assertions.assertEquals(3, list.head.value);
        Assertions.assertEquals(2, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(3, a.get(0).value);
        Assertions.assertEquals(2, a.get(1).value);
    }

    @Test
    public void deleteMidDesTest() {
        OrderedList<Integer> list = new OrderedList<>(false);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(2);
        Assertions.assertEquals(3, list.head.value);
        Assertions.assertEquals(1, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(3, a.get(0).value);
        Assertions.assertEquals(1, a.get(1).value);
    }

    @Test
    public void deleteTailDesTest() {
        OrderedList<Integer> list = new OrderedList<>(false);

        list.add(2);
        list.add(1);
        list.add(3);

        list.delete(3);
        Assertions.assertEquals(2, list.head.value);
        Assertions.assertEquals(1, list.tail.value);

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(2, a.size());
        Assertions.assertEquals(2, a.get(0).value);
        Assertions.assertEquals(1, a.get(1).value);
    }

    @Test
    public void deleteEmptyTest() {
        OrderedList<Integer> list = new OrderedList<>(false);

        list.delete(0);

        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
        Assertions.assertEquals(0, list.count());

        ArrayList<Node<Integer>> a = list.getAll();
        Assertions.assertEquals(0, a.size());
    }
}
