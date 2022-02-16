package task14;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class BSTNodeTest {
    @Test
    public void findNodeByKeyTest() {
        BSTNode<Integer> root = new BSTNode<>(100, 100, null);
        BST<Integer> bst = new BST<>(root);
    }

    @Test
    public void addKeyValueTest() {
        BSTNode<Integer> root = new BSTNode<>(100, 100, null);
        BST<Integer> bst = new BST<>(root);

        // already have such key
        Assertions.assertFalse(bst.AddKeyValue(100, 300));

        // add new 200 -------------
        Assertions.assertTrue(bst.AddKeyValue(200, 300));
        // check root 100:
        Assertions.assertEquals(100, bst.Root.NodeKey);
        Assertions.assertEquals(200, bst.Root.RightChild.NodeKey);
        Assertions.assertNull(bst.Root.LeftChild);
        // check root 200:
        BSTNode<Integer> x200 = bst.Root.RightChild;
        Assertions.assertEquals(200, x200.NodeKey);
        Assertions.assertEquals(root.NodeKey, x200.Parent.NodeKey);
        Assertions.assertNull(x200.LeftChild);
        Assertions.assertNull(x200.RightChild);


        // remove root 100 -----------
        Assertions.assertTrue(bst.DeleteNodeByKey(100));
        // check root 200:
        Assertions.assertEquals(200, bst.Root.NodeKey);
        Assertions.assertNull(bst.Root.LeftChild);
        Assertions.assertNull(bst.Root.RightChild);

        // remove root 200 -----------
        Assertions.assertTrue(bst.DeleteNodeByKey(200));
        // check root == null:
        Assertions.assertNull(bst.Root);

        // add root 500:
        Assertions.assertTrue(bst.AddKeyValue(500, 500));
        Assertions.assertEquals(500, bst.Root.NodeValue);
        Assertions.assertNull(bst.Root.LeftChild);
        Assertions.assertNull(bst.Root.RightChild);
    }

    @Test
    public void findMinMaxTest() {
        BSTNode<Integer> root = new BSTNode<>(100, 100, null);
        BST<Integer> bst = new BST<>(root);
        // already have such key
        Assertions.assertFalse(bst.AddKeyValue(100, 300));
        Assertions.assertTrue(bst.AddKeyValue(200, 300));
        Assertions.assertTrue(bst.AddKeyValue(300, 300));
        Assertions.assertTrue(bst.AddKeyValue(400, 300));
        Assertions.assertTrue(bst.AddKeyValue(500, 300));
        Assertions.assertTrue(bst.AddKeyValue(600, 300));
        Assertions.assertTrue(bst.AddKeyValue(700, 300));
        Assertions.assertEquals(700, bst.FinMinMax(root, true).NodeKey);
        Assertions.assertEquals(100, bst.FinMinMax(root, false).NodeKey);
        Assertions.assertTrue(bst.AddKeyValue(50, 300));
        Assertions.assertEquals(50, bst.FinMinMax(root, false).NodeKey);
    }

    /**
     * //   100
     * //      \
     * //       200
     * //          \
     * //           300
     * //          /   \
     * //        250   400
     * //------------------
     *
     * @return bst sample
     */
    BST<Integer> makeSample1() {
        BSTNode<Integer> root = new BSTNode<>(100, 0, null);
        BST<Integer> bst = new BST<>(root);
        Assertions.assertTrue(bst.AddKeyValue(200, 0));
        Assertions.assertTrue(bst.AddKeyValue(300, 0));
        // add left child of 300
        Assertions.assertTrue(bst.AddKeyValue(250, 0));
        // add right child of 300
        Assertions.assertTrue(bst.AddKeyValue(400, 0));
        return bst;
    }


    @Test
    public void deleteNodeByKey200TestS1() {
        BST<Integer> bst = makeSample1();
        // remove 200:
        Assertions.assertTrue(bst.DeleteNodeByKey(200));
        // check:
        Assertions.assertEquals(100, bst.Root.NodeKey);
        Assertions.assertEquals(250, bst.Root.RightChild.NodeKey);
        Assertions.assertEquals(300, bst.Root.RightChild.RightChild.NodeKey);
        Assertions.assertEquals(400, bst.Root.RightChild.RightChild.RightChild.NodeKey);
        Assertions.assertEquals(4, bst.Count());
    }

    @Test
    public void deleteNodeByKey100TestS1() {
        BST<Integer> bst = makeSample1();
        // remove 100:
        Assertions.assertTrue(bst.DeleteNodeByKey(100));
        // check:
        Assertions.assertEquals(200, bst.Root.NodeKey);
        Assertions.assertEquals(300, bst.Root.RightChild.NodeKey);
        Assertions.assertEquals(250, bst.Root.RightChild.LeftChild.NodeKey);
        Assertions.assertEquals(400, bst.Root.RightChild.RightChild.NodeKey);
        Assertions.assertEquals(4, bst.Count());
    }

    /**
     * //   100
     * //      \
     * //       200
     * //          \
     * //           300
     * //          /   \
     * //        250   400
     * //                 \
     * //                 500
     * //------------------
     *
     * @return bst sample2
     */
    BST<Integer> makeSample2() {
        BSTNode<Integer> root = new BSTNode<>(100, 0, null);
        BST<Integer> bst = new BST<>(root);
        Assertions.assertTrue(bst.AddKeyValue(200, 0));
        Assertions.assertTrue(bst.AddKeyValue(300, 0));
        // add left child of 300
        Assertions.assertTrue(bst.AddKeyValue(250, 0));
        // add right child of 300
        Assertions.assertTrue(bst.AddKeyValue(400, 0));
        // add right child of 400
        Assertions.assertTrue(bst.AddKeyValue(500, 0));
        return bst;
    }

    @Test
    public void deleteNodeByKey300TestS2() {
        BST<Integer> bst = makeSample2();
        // remove 300:
        Assertions.assertTrue(bst.DeleteNodeByKey(300));
        // check:
        Assertions.assertEquals(100, bst.Root.NodeKey);
        Assertions.assertEquals(200, bst.Root.RightChild.NodeKey);
        Assertions.assertEquals(400, bst.Root.RightChild.RightChild.NodeKey);
        Assertions.assertEquals(250, bst.Root.RightChild.RightChild.LeftChild.NodeKey);
        Assertions.assertEquals(500, bst.Root.RightChild.RightChild.RightChild.NodeKey);
        Assertions.assertEquals(5, bst.Count());
    }

    @Test
    public void countTest() {
        BSTNode<Integer> root = new BSTNode<>(100, 100, null);
        BST<Integer> bst = new BST<>(root);
        Assertions.assertEquals(1, bst.Count());

        // remove root 100
        Assertions.assertTrue(bst.DeleteNodeByKey(100));
        // check:
        Assertions.assertNull(bst.Root);
        Assertions.assertEquals(0, bst.Count());

        Assertions.assertTrue(bst.AddKeyValue(200, 200));
        Assertions.assertEquals(1, bst.Count());

        for (int i = 0; i < 10; i++)
            Assertions.assertTrue(bst.AddKeyValue(i, i * 10));

        Assertions.assertEquals(11, bst.Count());
    }

    @Test
    public void countTestS1() {
        BST<Integer> bst = makeSample1();
        Assertions.assertEquals(5, bst.Count());
    }

    @Test
    public void countTestS2() {
        BST<Integer> bst = makeSample2();
        Assertions.assertEquals(6, bst.Count());
    }

    /**
     * //      100
     * //     /    \
     * //   70     200
     * //  /  \       \
     * // 60  80      300
     * //            /   \
     * //          250   400
     * //          /        \
     * //       225           500
     * //------------------
     *
     * @return bst sample2
     */
    BST<Integer> makeSample3() {
        BSTNode<Integer> root = new BSTNode<>(100, 0, null);
        BST<Integer> bst = new BST<>(root);
        Assertions.assertTrue(bst.AddKeyValue(200, 0));
        Assertions.assertTrue(bst.AddKeyValue(300, 0));
        // add left child of 300
        Assertions.assertTrue(bst.AddKeyValue(250, 0));
        // add right child of 300
        Assertions.assertTrue(bst.AddKeyValue(400, 0));
        // add right child of 400
        Assertions.assertTrue(bst.AddKeyValue(500, 0));
        // add left child of 100
        Assertions.assertTrue(bst.AddKeyValue(70, 0));
        // add left child of 70
        Assertions.assertTrue(bst.AddKeyValue(60, 0));
        // add right child of 80
        Assertions.assertTrue(bst.AddKeyValue(80, 0));

        Assertions.assertTrue(bst.AddKeyValue(225, 0));
        return bst;
    }

    @Test
    public void testWideAllNodes() {
        BST<Integer> bst = makeSample3();
        ArrayList<BSTNode> res = bst.WideAllNodes();

        Assertions.assertEquals(100, res.get(0).NodeKey);
        Assertions.assertEquals(70, res.get(1).NodeKey);
        Assertions.assertEquals(200, res.get(2).NodeKey);
        Assertions.assertEquals(60, res.get(3).NodeKey);
        Assertions.assertEquals(80, res.get(4).NodeKey);
        Assertions.assertEquals(300, res.get(5).NodeKey);
        Assertions.assertEquals(250, res.get(6).NodeKey);
        Assertions.assertEquals(400, res.get(7).NodeKey);
        Assertions.assertEquals(225, res.get(8).NodeKey);
        Assertions.assertEquals(500, res.get(9).NodeKey);
    }

    @Test
    public void testDeepAllNodes() {
        BST<Integer> bst = makeSample3();
        ArrayList<BSTNode> res = bst.DeepAllNodes();

        Assertions.assertEquals(100, res.get(0).NodeKey);
        Assertions.assertEquals(70, res.get(1).NodeKey);
        Assertions.assertEquals(60, res.get(2).NodeKey);
        Assertions.assertEquals(80, res.get(3).NodeKey);
        Assertions.assertEquals(200, res.get(4).NodeKey);
        Assertions.assertEquals(300, res.get(5).NodeKey);
        Assertions.assertEquals(250, res.get(6).NodeKey);
        Assertions.assertEquals(225, res.get(7).NodeKey);
        Assertions.assertEquals(400, res.get(8).NodeKey);
        Assertions.assertEquals(500, res.get(9).NodeKey);
    }
}
