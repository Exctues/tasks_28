package task6;

import java.util.ArrayList;

public class Deque<T> {
    public LinkedList2<T> list;

    public Deque() {
        list = new LinkedList2<>();
    }

    public void addFront(T item) {
        list.insertAfter(null, new Node<>(item));
    }

    public void addTail(T item) {
        list.addInTail(new Node<>(item));
    }

    public T removeFront() {
        if (list.head != null) {
            T val = list.head.value;
            list.removeNode(list.head);
            return val;
        }

        return null;
    }

    public T removeTail() {
        if (list.tail != null) {
            T val = list.tail.value;
            list.removeNode(list.tail);
            return val;
        }

        return null;
    }

    public int size() {
        return list.count();
    }

    public static boolean isPalindrome(String s) {
        Deque<Character> deq = new Deque<>();
        for (int i = 0; i < s.length(); i++) {
            deq.addTail(s.charAt(i));
        }

        while(deq.size() > 1){
            char a = deq.removeFront();
            char b = deq.removeTail();
            if(a != b)
                return false;

        }

        return true;
    }
}

// copied from task2 and extended to support generic <T>
class LinkedList2<T> {
    public Node<T> head;
    public Node<T> tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node<T> _item) {
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

    public Node<T> find(T _value) {
        Node<T> node = head;
        while (node != null) {
            if (node.value == _value)
                return node;
            node = node.next;
        }

        return null;
    }

    public ArrayList<Node<T>> findAll(T _value) {
        ArrayList<Node<T>> nodes = new ArrayList<>();

        Node<T> node = head;
        while (node != null) {
            if (node.value == _value)
                nodes.add(node);
            node = node.next;
        }

        return nodes;
    }

    public boolean removeNode(Node<T> node) {
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

    public boolean remove(T _value) {
        Node<T> node = find(_value);
        return removeNode(node);
    }

    public void removeAll(T _value) {
        ArrayList<Node<T>> nodes = findAll(_value);
        for (Node<T> node : nodes) {
            removeNode(node);
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        Node<T> node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    public void insertAfter(Node<T> _nodeAfter, Node<T> _nodeToInsert) {
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

class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}
