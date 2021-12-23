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


        Assertions.assertSame(null, list.find(0));
        Assertions.assertSame(node1, list.find(1));
        Assertions.assertSame(node2, list.find(2));
        Assertions.assertSame(node3, list.find(3));
        Assertions.assertSame(null, list.find(4));
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

        Assertions.assertSame(list.find(2), node2);
        Assertions.assertSame(true, list.remove(2));
        Assertions.assertSame(null, list.find(2));
        Assertions.assertSame(false, list.remove(2));
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

        // TC2
        list.insertAfter(node1, node10);
        Assertions.assertSame(list.head, node1);
        Assertions.assertSame(4, list.count());
        Assertions.assertSame(node10, list.find(10));

        Assertions.assertSame(true, list.remove(10));
        Assertions.assertSame(list.count(), 3);
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
        Assertions.assertSame(null, list.find(3));
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

        Assertions.assertSame(3, list.count());
        list.clear();
        Assertions.assertSame(0, list.count());
    }

    @Test
    public void testEmpty() {
        LinkedList list = new LinkedList();
        list.clear();

        list.remove(1);
        list.removeAll(1);
        list.find(1);

        Assertions.assertSame(0, list.count());
        Assertions.assertSame(null, list.head);
        Assertions.assertSame(null, list.tail);
    }
}
