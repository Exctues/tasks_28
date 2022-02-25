package task17;

import java.util.*;

class BSTNode {
    public int NodeKey; // ключ узла
    public BSTNode Parent; // родитель или null для корня
    public BSTNode LeftChild; // левый потомок
    public BSTNode RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode(int key, BSTNode parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    class BalancedResult {
        int height = 0;
        boolean isBalanced = true;
    }

    public BSTNode Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    private BSTNode helper(int[] a, BSTNode prev, int l, int r) {
        if (r <= l)
            return null;

        int midIdx = (l + r) / 2;
        BSTNode node = new BSTNode(a[midIdx], prev);
        if (prev == null) {
            node.Level = 0;
        } else {
            node.Level = prev.Level + 1;
        }
        node.LeftChild = helper(a, node, l, midIdx);  // left child
        node.RightChild = helper(a, node, midIdx + 1, r);  // right child

        return node;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        Root = helper(a, null, 0, a.length);
    }

    // return height of particular node
    private BalancedResult getBalanceResult(BSTNode node) {
        if (node == null)
            return new BalancedResult();
        BalancedResult leftRes = getBalanceResult(node.LeftChild);
        BalancedResult rightRes = getBalanceResult(node.RightChild);
        boolean leftIsSmaller = node.LeftChild == null || node.LeftChild.NodeKey < node.NodeKey;
        boolean rightIsEqualOrBigger = node.RightChild == null || node.RightChild.NodeKey >= node.NodeKey;
        boolean smallHeightDiff = Math.abs(leftRes.height - rightRes.height) <= 1;
        BalancedResult r = new BalancedResult();
        r.height = Math.max(leftRes.height, rightRes.height) + 1;
        r.isBalanced = leftIsSmaller && rightIsEqualOrBigger && leftRes.isBalanced && rightRes.isBalanced && smallHeightDiff;
        return r;
    }

    public boolean IsBalanced(BSTNode root_node) {
        BalancedResult r = getBalanceResult(root_node);
        return r.isBalanced;
    }
}