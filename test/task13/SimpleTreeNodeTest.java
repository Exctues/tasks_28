package task13;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SimpleTreeNodeTest {
    @Test
    public void testAdd() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        Assertions.assertEquals(1, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());

        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(1, null);
        tree.AddChild(root, child);
        Assertions.assertEquals(2, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());
        Assertions.assertEquals(root, child.Parent);

        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, null);
        tree.AddChild(root, child2);
        Assertions.assertEquals(3, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(root, child2.Parent);
    }

    @Test
    public void testDelete() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        Assertions.assertEquals(1, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());

        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(1, null);
        tree.AddChild(root, child);
        Assertions.assertEquals(2, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());
        Assertions.assertEquals(root, child.Parent);

        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, null);
        tree.AddChild(root, child2);
        Assertions.assertEquals(3, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(root, child2.Parent);

        tree.DeleteNode(root);
        Assertions.assertEquals(0, tree.Count());
        Assertions.assertEquals(0, tree.LeafCount());
    }

    @Test
    public void testFindByValue() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        Assertions.assertEquals(1, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());

        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(1, null);
        tree.AddChild(root, child);
        Assertions.assertEquals(2, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());
        Assertions.assertEquals(root, child.Parent);

        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, null);
        tree.AddChild(root, child2);
        Assertions.assertEquals(3, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(root, child2.Parent);

        for (int i = 0; i < 3; i++) {
            List<SimpleTreeNode<Integer>> l = tree.FindNodesByValue(i);
            Assertions.assertEquals(1, l.size());
            Assertions.assertEquals(i, l.get(0).NodeValue);
        }
    }

    @Test
    public void testMoveNode() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(0, null);
        SimpleTree<Integer> tree = new SimpleTree<>(root);
        Assertions.assertEquals(1, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());

        SimpleTreeNode<Integer> child = new SimpleTreeNode<>(1, null);
        tree.AddChild(root, child);
        Assertions.assertEquals(2, tree.Count());
        Assertions.assertEquals(1, tree.LeafCount());
        Assertions.assertEquals(root, child.Parent);

        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, null);
        tree.AddChild(root, child2);
        Assertions.assertEquals(3, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(root, child2.Parent);

        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, null);
        tree.AddChild(child2, child3);
        Assertions.assertEquals(4, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(child2, child3.Parent);

        tree.MoveNode(child3, child);
        Assertions.assertEquals(4, tree.Count());
        Assertions.assertEquals(2, tree.LeafCount());
        Assertions.assertEquals(child, child3.Parent);
    }
}
