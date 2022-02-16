package task14;

import java.io.*;
import java.util.*;

class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок

    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null

    public BST(BSTNode<T> node) {
        Root = node;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTNode<T> node = Root;
        BSTNode<T> prev = null;
        while (node != null && node.NodeKey != key) {
            prev = node;
            node = (key > node.NodeKey) ? node.RightChild : node.LeftChild;
        }

        BSTFind<T> res = new BSTFind<>();
        res.Node = (node != null) ? node : prev;
        res.NodeHasKey = (node != null);
        if (node == null)
            res.ToLeft = (prev != null && prev.NodeKey > key);

        return res;
    }

    public boolean AddKeyValue(int key, T val) {
        if (Root == null) {
            Root = new BSTNode<>(key, val, null);
            return true;
        }

        BSTFind<T> res = FindNodeByKey(key);
        if (res.NodeHasKey)
            return false;

        BSTNode<T> newNode = new BSTNode<>(key, val, res.Node);
        if (res.ToLeft)
            res.Node.LeftChild = newNode;
        else
            res.Node.RightChild = newNode;

        return true; // если ключ уже есть
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> node = FromNode;
        BSTNode<T> prev = null;
        while (node != null) {
            prev = node;
            node = FindMax ? node.RightChild : node.LeftChild;
        }
        return prev;
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> res = FindNodeByKey(key);
        if (!res.NodeHasKey)
            return false;
        BSTNode<T> nodeToRemove = res.Node;

        BSTNode<T> nodeReplacer = FinMinMax(res.Node.RightChild, false);
        if (nodeReplacer == null) {
            // there is NO node which can substitute nodeToRemove
            if (nodeToRemove.LeftChild != null)
                nodeToRemove.LeftChild.Parent = null;
            if (nodeToRemove.RightChild != null)
                nodeToRemove.RightChild.Parent = null;
            if (nodeToRemove.Parent != null) {
                if (nodeToRemove.Parent.LeftChild == nodeToRemove)
                    nodeToRemove.Parent.LeftChild = null;
                else
                    nodeToRemove.Parent.RightChild = null;
            }

            if (nodeToRemove == Root)
                Root = null;
            return true;
        }

        // | there is Node which can substitute nodeToRemove, 2 cases
        // | 1) it is leaf
        // | 2) it has right child
        assert nodeReplacer.LeftChild == null;

//        System.out.println("raplacer: " + nodeReplacer.NodeKey);
        if (nodeReplacer.RightChild != null) {
            // right child exist
//            System.out.println("right: " + nodeReplacer.RightChild.NodeKey);

            // disconnect from prev Parent
            // and move it's right child to its old place
            if (nodeToRemove != nodeReplacer.Parent) {
                // (2)
                if (nodeReplacer.Parent.LeftChild == nodeReplacer)
                    nodeReplacer.Parent.LeftChild = nodeReplacer.RightChild;
                else
                    nodeReplacer.Parent.RightChild = nodeReplacer.RightChild;
                nodeReplacer.RightChild.Parent = nodeReplacer.Parent;
            }

            // connect to new parent
            if (nodeToRemove.Parent != null) {
                if (nodeToRemove.Parent.LeftChild == nodeToRemove)
                    nodeToRemove.Parent.LeftChild = nodeReplacer;
                else
                    nodeToRemove.Parent.RightChild = nodeReplacer;
            } else {
                Root = nodeReplacer;
            }
            nodeReplacer.Parent = nodeToRemove.Parent;

            // fix children, left keep same
            nodeReplacer.LeftChild = nodeToRemove.LeftChild;


        } else {
            // it is a leaf

            // disconnect from prev Parent
            if (nodeReplacer.Parent.LeftChild == nodeReplacer)
                nodeReplacer.Parent.LeftChild = null;
            else
                nodeReplacer.Parent.RightChild = null;

            // connect to new parent
            if (nodeToRemove.Parent != null) {
                if (nodeToRemove.Parent.LeftChild == nodeToRemove)
                    nodeToRemove.Parent.LeftChild = nodeReplacer;
                else
                    nodeToRemove.Parent.RightChild = nodeReplacer;
            } else {
                Root = nodeReplacer;
            }
            nodeReplacer.Parent = nodeToRemove.Parent;


            // fix children, left keep same
            nodeReplacer.LeftChild = nodeToRemove.LeftChild;
            // copy right correctly
            if (nodeToRemove.RightChild != nodeReplacer)
                nodeReplacer.RightChild = nodeToRemove.RightChild;
        }

        return true;
    }

    private int Count(BSTNode<T> node) {
        if (node == null)
            return 0;
        return 1 + Count(node.LeftChild) + Count(node.RightChild);
    }

    public int Count() {
        return Count(Root);
    }

    public ArrayList<BSTNode> WideAllNodes() {
        if (Root == null)
            return new ArrayList<>();

        ArrayList<BSTNode> res = new ArrayList<>();

        Queue<BSTNode> q = new ArrayDeque<>();
        q.add(Root);
        while (!q.isEmpty()) {
            BSTNode node = q.poll();
            res.add(node);
            if (node.LeftChild != null)
                q.offer(node.LeftChild);
            if (node.RightChild != null)
                q.offer(node.RightChild);
        }

        return res;
    }

    private void deepHelperPre(BSTNode current, ArrayList<BSTNode> res) {
        if (current == null)
            return;
        res.add(current);
        deepHelperPre(current.LeftChild, res);
        deepHelperPre(current.RightChild, res);
    }

    private void deepHelperIn(BSTNode current, ArrayList<BSTNode> res) {
        if (current == null)
            return;
        deepHelperIn(current.LeftChild, res);
        res.add(current);
        deepHelperIn(current.RightChild, res);
    }

    private void deepHelperPost(BSTNode current, ArrayList<BSTNode> res) {
        if (current == null)
            return;
        deepHelperPost(current.LeftChild, res);
        deepHelperPost(current.RightChild, res);
        res.add(current);
    }


    public ArrayList<BSTNode> DeepAllNodes(int method) {
        ArrayList<BSTNode> res = new ArrayList<>();

        if (method == 0)
            deepHelperIn(Root, res);
        else if (method == 1)
            deepHelperPost(Root, res);
        else
            deepHelperPre(Root, res);

        return res;
    }
}