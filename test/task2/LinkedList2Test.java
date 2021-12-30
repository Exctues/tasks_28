package task2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import task1.LinkedList;

import java.util.ArrayList;

public class LinkedList2Test {

    public void constructTest() {
        LinkedList2 list = new LinkedList2();

        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testAddInTail() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);

        Assertions.assertSame(node2, node1.next);
        Assertions.assertSame(null, node1.prev);

        Assertions.assertSame(node1, node2.prev);
        Assertions.assertSame(node3, node2.next);

        Assertions.assertSame(node2, node3.prev);
        Assertions.assertSame(null, node3.next);
    }

    @Test
    public void testFind() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        Assertions.assertNull(list.find(1));

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        Assertions.assertSame(node1, list.find(1));
        Assertions.assertSame(node2, list.find(2));
        Assertions.assertSame(node3, list.find(3));

        Assertions.assertNull(list.find(4));
    }

    @Test
    public void testFindAll() {
        LinkedList2 list = new LinkedList2();
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
    public void testRemove() {
        LinkedList2 list = new LinkedList2();
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
        Assertions.assertSame(node4, list.tail);
        Assertions.assertSame(node3, node1.next);
        Assertions.assertSame(node1, node3.prev);
        Assertions.assertSame(node4, node3.next);
        Assertions.assertSame(node3, node4.prev);
        Assertions.assertNull(list.tail.next);

        // we have:         | 1 -> 3 -> 4
        // remove 1 to get: | 3 -> 4
        Assertions.assertSame(true, list.remove(1));
        Assertions.assertSame(false, list.remove(1));
        Assertions.assertNull(list.find(1));
        Assertions.assertSame(2, list.count());
        Assertions.assertSame(node3, list.head);
        Assertions.assertSame(null, list.head.prev);
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
        Assertions.assertNull(list.head.prev);


        // we have:         | 3
        // remove 4 to get: | empty
        Assertions.assertSame(true, list.remove(3));
        Assertions.assertSame(false, list.remove(3));
        Assertions.assertSame(0, list.count());
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }


    @Test
    public void testRemoveAllEmpty() {
        LinkedList2 list = new LinkedList2();
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
        list.removeAll(1);
        Assertions.assertNull(list.head);
        Assertions.assertNull(list.tail);
    }

    @Test
    public void testRemoveAllInMiddle() {
        LinkedList2 list = new LinkedList2();
        Node node1_1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5_1 = new Node(1);

        list.addInTail(node1_1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);
        list.addInTail(node5_1);

        Assertions.assertSame(5, list.count());

        // from 1 2 3 2 1
        //   to 1 3 1
        list.removeAll(2);

        Assertions.assertSame(3, list.count());

        Assertions.assertSame(list.head, node1_1);
        Assertions.assertSame(null, node1_1.prev);
        Assertions.assertSame(node3, node1_1.next);

        Assertions.assertSame(node1_1, node3.prev);
        Assertions.assertSame(node5_1, node3.next);

        Assertions.assertSame(list.tail, node5_1);
        Assertions.assertSame(node3, node5_1.prev);
        Assertions.assertSame(null, node5_1.next);
    }

    @Test
    public void testRemoveAllInCorners() {
        LinkedList2 list = new LinkedList2();
        Node node1_1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(2);
        Node node5_1 = new Node(1);

        list.addInTail(node1_1);
        list.addInTail(node2);
        list.addInTail(node3);
        list.addInTail(node4);
        list.addInTail(node5_1);

        Assertions.assertSame(5, list.count());

        list.removeAll(1);

        Assertions.assertSame(3, list.count());

        Assertions.assertSame(list.head, node2);
        Assertions.assertSame(null, node2.prev);
        Assertions.assertSame(node3, node2.next);

        Assertions.assertSame(node2, node3.prev);
        Assertions.assertSame(node4, node3.next);

        Assertions.assertSame(list.tail, node4);
        Assertions.assertSame(node3, node4.prev);
        Assertions.assertSame(null, node4.next);
    }

    @Test
    public void testClear() {
        LinkedList2 list = new LinkedList2();
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
    public void testRemoveManyElements() {
        LinkedList2 list = new LinkedList2();
        for (int i = 0; i < 100; i++) {
            Node node = new Node(i % 4);
            list.addInTail(node);
        }
        Assertions.assertSame(100, list.count());

        list.removeAll(2);
        Assertions.assertSame(75, list.count());
    }

    @Test
    public void testInsertAfter() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node10 = new Node(10);

        list.addInTail(node1);
        list.addInTail(node2);
        list.addInTail(node3);

        // TC1
        // from:    1 2 3
        //   to: 10 1 2 3
        list.insertAfter(null, node10);
        Assertions.assertSame(list.head, node10);
        Assertions.assertSame(list.tail, node3);
        Assertions.assertSame(4, list.count());

        Assertions.assertSame(null, node10.prev);
        Assertions.assertSame(node1, node10.next);
        Assertions.assertSame(node10, node1.prev);
        Assertions.assertSame(node2, node1.next);
        Assertions.assertSame(node1, node2.prev);
        Assertions.assertSame(node3, node2.next);
        Assertions.assertSame(node2, node3.prev);
        Assertions.assertSame(null, node3.next);

        Assertions.assertSame(true, list.remove(10));
        Assertions.assertSame(3, list.count());
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);

        // TC2
        // from: 1    2 3
        //   to: 1 10 2 3
        list.insertAfter(node1, node10);
        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(list.tail, node3);
        Assertions.assertSame(4, list.count());
        Assertions.assertSame(node10, list.find(10));

        Assertions.assertSame(null, node1.prev);
        Assertions.assertSame(node10, node1.next);
        Assertions.assertSame(node1, node10.prev);
        Assertions.assertSame(node2, node10.next);
        Assertions.assertSame(node10, node2.prev);
        Assertions.assertSame(node3, node2.next);
        Assertions.assertSame(node2, node3.prev);
        Assertions.assertSame(null, node3.next);

        Assertions.assertSame(true, list.remove(10));
        Assertions.assertSame(list.count(), 3);
        Assertions.assertSame(node1, list.head);
        Assertions.assertSame(node3, list.tail);
    }

    @Test
    public void testInsertAfterLast() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);
        list.addInTail(node1);


        Node node2 = new Node(10);
        list.insertAfter(node1, node2);

        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(list.tail, node2);

        Assertions.assertSame(null, node1.prev);
        Assertions.assertSame(node2, node1.next);

        Assertions.assertSame(node1, node2.prev);
        Assertions.assertSame(null, node2.next);
    }

    @Test
    public void testInsertBeforeLast() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        list.addInTail(node1);
        list.addInTail(node3);

        Node node2 = new Node(2);
        // from: 1 3
        //   to: 1 2 3
        list.insertAfter(node1, node2);

        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(null, node1.prev);
        Assertions.assertSame(node2, node1.next);
        Assertions.assertSame(node1, node2.prev);
        Assertions.assertSame(node3, node2.next);
        Assertions.assertSame(node2, node3.prev);
        Assertions.assertSame(null, node3.next);
        Assertions.assertSame(list.tail, node3);
    }

    @Test
    public void testInsertAfterSingle() {
        LinkedList2 list = new LinkedList2();
        Node node1 = new Node(1);

        list.insertAfter(null, node1);

        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(list.tail, node1);
        Assertions.assertSame(1, list.count());
    }
}
