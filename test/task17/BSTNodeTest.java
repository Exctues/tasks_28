package task17;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BSTNodeTest {
    @Test
    //       3
    //    1     5
    //  0   2  4  6
    public void testConstruction() {
        int[] a = {0, 1, 2, 3, 4, 5, 6};
        BalancedBST bst = new BalancedBST();
        bst.GenerateTree(a);

        Assertions.assertTrue(bst.IsBalanced(bst.Root));
        Assertions.assertEquals(3, bst.Root.NodeKey);
        Assertions.assertEquals(1, bst.Root.LeftChild.NodeKey);
        Assertions.assertEquals(5, bst.Root.RightChild.NodeKey);
        Assertions.assertEquals(0, bst.Root.LeftChild.LeftChild.NodeKey);
        Assertions.assertEquals(2, bst.Root.LeftChild.RightChild.NodeKey);
        Assertions.assertEquals(4, bst.Root.RightChild.LeftChild.NodeKey);
        Assertions.assertEquals(6, bst.Root.RightChild.RightChild.NodeKey);
    }

    @Test
    //       100
    //          90
    public void testBalancedRightFalse() {
        BalancedBST bst = new BalancedBST();
        bst.Root = new BSTNode(100, null);
        bst.Root.RightChild = new BSTNode(90, bst.Root);

        Assertions.assertFalse(bst.IsBalanced(bst.Root));
    }

    @Test
    //       100
    //          90
    public void testBalancedLeftFalse() {
        BalancedBST bst = new BalancedBST();
        bst.Root = new BSTNode(100, null);
        bst.Root.LeftChild = new BSTNode(110, bst.Root);

        Assertions.assertFalse(bst.IsBalanced(bst.Root));
    }

    @Test
    //       100
    //          90
    public void testBalancedHeightFalse() {
        BalancedBST bst = new BalancedBST();
        bst.Root = new BSTNode(100, null);
        bst.Root.LeftChild = new BSTNode(90, bst.Root);
        bst.Root.LeftChild.LeftChild = new BSTNode(60, bst.Root.LeftChild);

        Assertions.assertFalse(bst.IsBalanced(bst.Root));
    }
}
