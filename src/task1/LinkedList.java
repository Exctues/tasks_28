package task1;

import java.util.*;

public class LinkedList {
    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node find(int value) {
        Node node = head;
        while (node != null) {
            if (node.value == value)
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

    public boolean remove(int _value) {
        Node node = head;
        Node prev = null;
        while (node != null) {
            if (node.value == _value) {
                removeNode(prev, node);
                return true;
            }
            prev = node;
            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        Node node = head;
        Node prev = null;
        while (node != null) {
            if (node.value == _value) {
                removeNode(prev, node);
                node = node.next;
                continue;
            }
            prev = node;
            node = node.next;
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        int n = 0;
        Node node = head;
        while (node != null) {
            node = node.next;
            n++;
        }
        return n;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            if (head == null) {
                assert tail == null;
                tail = _nodeToInsert;
            }
            _nodeToInsert.next = head;
            head = _nodeToInsert;
        }
        else {
            _nodeToInsert.next = _nodeAfter.next;
            _nodeAfter.next = _nodeToInsert;
            if (_nodeAfter == tail)
                tail = _nodeToInsert;
        }
    }

    private void removeNode(Node _previousNode, Node _nodeToRemove) {
        if (_nodeToRemove == head) {
            assert (_previousNode == null);
            head = _nodeToRemove.next;
            if (head == null)
                tail = null;
        } else if (_nodeToRemove == tail) {
            assert (tail.next == null);
            tail = _previousNode;
            tail.next = null;
        } else {
            assert (_previousNode != null && _nodeToRemove != null);
            _previousNode.next = _nodeToRemove.next;
        }
    }


    public static LinkedList sumCorrespondingElementsOfList(LinkedList _a, LinkedList _b) {
        if (_a.count() != _b.count())
            return null;

        LinkedList list = new LinkedList();
        Node aNode = _a.head;
        Node bNode = _b.head;
        while (aNode != null && bNode != null) {
            list.addInTail(new Node(aNode.value + bNode.value));
            aNode = aNode.next;
            bNode = bNode.next;
        }

        return list;
    }
}

class Node {
    public int value;
    public Node next;

    public Node(int _value) {
        value = _value;
        next = null;
    }
}