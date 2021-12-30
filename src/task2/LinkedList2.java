package task2;

import java.util.*;

public class LinkedList2 {
    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        Node node = head;
        while (node != null) {
            if (node.value == _value)
                return node;
            node = node.next;
        }

        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<>();

        Node node = head;
        while (node != null) {
            if (node.value == _value)
                nodes.add(node);
            node = node.next;
        }

        return nodes;
    }

    public boolean removeNode(Node node) {
        if (node == null)
            return false;

        if (node == head) {
            assert (node.prev == null);
            head = node.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;

        } else if (node == tail) {
            assert (node.next == null);
            tail = node.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
        } else {
            assert (node.next != null);
            assert (node.prev != null);

            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        return true;
    }

    public boolean remove(int _value) {
        Node node = find(_value);
        return removeNode(node);
    }

    public void removeAll(int _value) {
        ArrayList<Node> nodes = findAll(_value);
        for (Node node : nodes) {
            removeNode(node);
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        Node node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        // empty case
        if (head == null) {
            addInTail(_nodeToInsert);
            return;
        }

        // put new node as leftmost (before head)
        if (_nodeAfter == null) {
            _nodeToInsert.next = head;
            _nodeToInsert.prev = null;
            head.prev = _nodeToInsert;
            head = _nodeToInsert;
            return;
        }

        // put new node as rightmost (after tail)
        if (_nodeAfter == tail) {
            _nodeToInsert.prev = tail;
            _nodeToInsert.next = null;
            tail.next = _nodeToInsert;
            tail = _nodeToInsert;
            return;
        }

        // put new node in between head and tail
        _nodeToInsert.prev = _nodeAfter;
        _nodeToInsert.next = _nodeAfter.next;
        _nodeAfter.next.prev = _nodeToInsert;
        _nodeAfter.next = _nodeToInsert;
    }
}

class Node {
    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}