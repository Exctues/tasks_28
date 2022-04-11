package task23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WeakTest {
    @Test
    public void weakTest1() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 0);
        Assertions.assertEquals(0, g.WeakVertices().size());
    }

    @Test
    public void weakTest2() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 0);
        g.AddEdge(3, 0);
        Assertions.assertEquals(1, g.WeakVertices().size());
        Assertions.assertEquals(3, g.WeakVertices().get(0).Value);
    }

    @Test
    public void weakTest3() {
        SimpleGraph g = new SimpleGraph(2);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddEdge(0, 1);
        Assertions.assertEquals(2, g.WeakVertices().size());
    }

    @Test
    public void weakTest4() {
        SimpleGraph g = new SimpleGraph(1);
        g.AddVertex(0);
        Assertions.assertEquals(1, g.WeakVertices().size());
    }

    @Test
    public void weakTest5() {
        SimpleGraph g = new SimpleGraph(0);
        Assertions.assertEquals(0, g.WeakVertices().size());
    }

    @Test
    public void weakTest6() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddEdge(0, 1);
        g.AddEdge(2, 3);
        Assertions.assertEquals(4, g.WeakVertices().size());
    }

    @Test
    public void weakTest7() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddEdge(0, 1);
        g.AddEdge(1, 2);
        g.AddEdge(2, 3);
        Assertions.assertEquals(4, g.WeakVertices().size());
    }
}
