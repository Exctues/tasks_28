package task7;

import java.util.*;


class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;
    private int _count;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _count = 0;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if (v1 == v2)
            return 0;

        if (v1 instanceof String)
            return (((String) v1).trim()).compareTo(((String) v2).trim());

        if (v1 instanceof Integer)
            return ((Integer) v1).compareTo((Integer) v2);

        return 0;
        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
    }

    public void add(T value) {
        if (head == null) {
            head = new Node<>(value);
            tail = head;
            _count++;
            return;
        }

        boolean inserted = false;
        Node<T> node = head;
        while (node != null) {
            if ((_ascending && compare(value, node.value) <= 0) ||
                    (!_ascending && compare(value, node.value) >= 0)) {
                Node<T> newNode = new Node<>(value);
                if (node != head) {
                    node.prev.next = newNode;
                    newNode.prev = node.prev;
                } else
                    head = newNode;

                newNode.next = node;
                node.prev = newNode;
                inserted = true;
                break;
            }
            node = node.next;
        }

        if (!inserted) {
            // put at the end
            tail.next = new Node<>(value);
            tail.next.prev = tail;
            tail = tail.next;
        }

        _count++;

    }

    public Node<T> find(T val) {
        Node<T> node = head;
        while (node != null) {
            if (compare(val, node.value) == 0)
                return node;
            node = node.next;
        }
        return null;
    }

    public void delete(T val) {
        Node<T> nodeToDelete = find(val);
        if (nodeToDelete == null)
            return;

        if (nodeToDelete != head)
            nodeToDelete.prev.next = nodeToDelete.next;
        else
            head = nodeToDelete.next;
        if (nodeToDelete != tail)
            nodeToDelete.next.prev = nodeToDelete.prev;
        else
            tail = nodeToDelete.prev;

        _count--;
    }

    public void clear(boolean asc) {
        _ascending = asc;
        _count = 0;
        head = null;
        tail = null;
    }

    public int count() {
        return _count;
    }

    ArrayList<Node<T>> getAll() {
        // выдать все элементы упорядоченного
        // списка в виде стандартного списка
        ArrayList<Node<T>> r = new ArrayList<>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }
}