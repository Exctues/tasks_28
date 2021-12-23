package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LinkedListTest {
    @Test
    public void testAddInTail() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);

        Assertions.assertSame(node2, node1.next);
        Assertions.assertSame(node3, node2.next);
        Assertions.assertNull(node3.next);
    }

    @Test
    public void testFind() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);


        Assertions.assertNull(list.find(0));
        Assertions.assertSame(node1, list.find(1));
        Assertions.assertSame(node2, list.find(2));
        Assertions.assertSame(node3, list.find(3));
        Assertions.assertNull(list.find(4));
    }

    @Test
    public void testRemove() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);

        // we have:         | 1 -> 2 -> 3 -> 4
        // remove 2 to get: | 1 -> 3 -> 4
        Assertions.assertSame(list.find(2), node2);
        Assertions.assertSame(true, list.remove(2));
        Assertions.assertSame(false, list.remove(2));
        Assertions.assertNull(list.find(2));
        Assertions.assertSame(3, list.count());
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, node1.next);
        Assertions.assertSame(node4, node3.next);
        Assertions.assertSame(node4, list.tail);
        Assertions.assertNull(list.tail.next);

        // we have:         | 1 -> 3 -> 4
        // remove 1 to get: | 3 -> 4
        Assertions.assertSame(true, list.remove(1));
        Assertions.assertSame(false, list.remove(1));
        Assertions.assertNull(list.find(1));
        Assertions.assertSame(2, list.count());
        Assertions.assertSame(node3, list.head);
        Assertions.assertSame(node4, list.head.next);
        Assertions.assertSame(node4, list.tail);
        Assertions.assertNull(list.tail.next);

        // we have:         | 3 -> 4
        // remove 4 to get: | 3
        Assertions.assertSame(true, list.remove(4));
        Assertions.assertSame(false, list.remove(4));
        Assertions.assertSame(1, list.count());
        Assertions.assertSame(list.tail, list.head);
        Assertions.assertSame(node3, list.head);
        Assertions.assertNull(list.head.next);


        // we have:         | 3
        // remove 4 to get: | empty
        Assertions.assertSame(true, list.remove(3));
        Assertions.assertSame(false, list.remove(3));
        Assertions.assertSame(0, list.count());
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testRemoveSingleElement() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);

        list.addInTail(node1);

        Assertions.assertSame(1, list.count());
        Assertions.assertSame(true, list.remove(1));
        Assertions.assertSame(0, list.count());
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testInsertAfter() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node10 = new Node(10);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        // TC1
        list.insertAfter(null, node10);
        Assertions.assertSame(list.head, node10);
        Assertions.assertSame(4, list.count());

        Assertions.assertSame(true, list.remove(10));
        Assertions.assertSame(3, list.count());
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);

        // TC2
        list.insertAfter(node1, node10);
        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(4, list.count());
        Assertions.assertSame(node10, list.find(10));

        Assertions.assertSame(true, list.remove(10));
        Assertions.assertSame(list.count(), 3);
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);
    }

    @Test
    public void testRemoveAll() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node3_1 = new Node(3);
        Node node3_2 = new Node(3);


        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node3_1);
        list.addInTail(node3_2);

        list.removeAll(3);
        Assertions.assertSame(2, list.count());
        Assertions.assertNull(list.find(3));
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node2, list.tail);
    }

    @Test
    public void testFindAll() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2_1 = new Node(2);
        Node node3 = new Node(3);
        Node node2_2 = new Node(2);


        list.addInTail(node1);
        list.addInTail(node2_1);
        list.addInTail(node3);
        list.addInTail(node2_2);
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node2_2, list.tail);
        Assertions.assertSame(4, list.count());

        ArrayList<Node> nodes = list.findAll(2);
        Assertions.assertSame(2, nodes.size());

        Assertions.assertSame(node2_1, nodes.get(0));
        Assertions.assertSame(node2_2, nodes.get(1));
        Assertions.assertSame(2, node2_1.value);
        Assertions.assertSame(2, node2_2.value);
    }

    @Test
    public void testClear() {
        LinkedList list = new LinkedList();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);

        Assertions.assertSame(3, list.count());
        list.clear();
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testEmpty() {
        LinkedList list = new LinkedList();
        list.clear();

        list.remove(1);
        list.removeAll(1);
        list.find(1);

        Assertions.assertSame(0, list.count());
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testRemoveManyElements() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            Node node = new Node(i % 4);
            list.addInTail(node);
        }
        Assertions.assertSame(100, list.count());

        list.removeAll(2);
        Assertions.assertSame(75, list.count());
    }

    @Test
    public void testSumCorrespondingElementsOfList() {
        LinkedList listA = new LinkedList();
        listA.addInTail(new Node(1));
        listA.addInTail(new Node(2));
        listA.addInTail(new Node(3));

        LinkedList listB = new LinkedList();
        listB.addInTail(new Node(10));
        listB.addInTail(new Node(20));
        listB.addInTail(new Node(30));

        Assertions.assertSame(3, listA.count());
        Assertions.assertSame(3, listB.count());

        LinkedList resultList = LinkedList.sumCorrespondingElementsOfList(listA, listB);
        Assertions.assertNotNull(resultList);
        Assertions.assertSame(3, resultList.count());
        Assertions.assertNotNull(resultList.find(11));
        Assertions.assertNotNull(resultList.find(22));
        Assertions.assertNotNull(resultList.find(33));
    }

}
