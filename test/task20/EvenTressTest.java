package task20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class EvenTressTest {

    @Test
    public void testBaseCase() {
        SimpleTreeNode<Integer> root = new SimpleTreeNode<>(1, null);
        SimpleTree<Integer> t = new SimpleTree<>(root);

        // children of root
        SimpleTreeNode<Integer> child2 = new SimpleTreeNode<>(2, root);
        SimpleTreeNode<Integer> child3 = new SimpleTreeNode<>(3, root);
        SimpleTreeNode<Integer> child6 = new SimpleTreeNode<>(6, root);
        t.AddChild(root, child2);
        t.AddChild(root, child3);
        t.AddChild(root, child6);

        // children of 2
        SimpleTreeNode<Integer> child5 = new SimpleTreeNode<>(5, child2);
        SimpleTreeNode<Integer> child7 = new SimpleTreeNode<>(7, child2);
        t.AddChild(child2, child5);
        t.AddChild(child2, child7);

        // children of 2
        SimpleTreeNode<Integer> child4 = new SimpleTreeNode<>(4, child3);
        t.AddChild(child3, child4);

        // children of 6
        SimpleTreeNode<Integer> child8 = new SimpleTreeNode<>(8, child6);
        t.AddChild(child6, child8);

        // children of 8
        SimpleTreeNode<Integer> child9 = new SimpleTreeNode<>(9, child8);
        SimpleTreeNode<Integer> child10 = new SimpleTreeNode<>(10, child8);
        t.AddChild(child8, child9);
        t.AddChild(child8, child10);

        ArrayList<Integer> evenTrees = t.EvenTrees();
        Assertions.assertEquals(0, evenTrees.size() % 2);
        Assertions.assertEquals(1, evenTrees.get(0));
        Assertions.assertEquals(6, evenTrees.get(1));
        Assertions.assertEquals(1, evenTrees.get(2));
        Assertions.assertEquals(3, evenTrees.get(3));
//        System.out.println(evenTrees.toString());
    }
}
