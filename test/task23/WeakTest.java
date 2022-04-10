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
}
