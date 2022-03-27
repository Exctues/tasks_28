package task19;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VertexTest {

    @Test
    public void addTest() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        Assertions.assertEquals(0, g.vertex[0].Value);
        for (int i = 0; i < g.max_vertex; i++)
            Assertions.assertFalse(g.IsEdge(i, 0));
    }

    @Test
    public void addEdgeTest() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        g.AddVertex(1);

        Assertions.assertFalse(g.IsEdge(0, 1));
        Assertions.assertFalse(g.IsEdge(1, 0));
        g.AddEdge(0, 1);
        Assertions.assertTrue(g.IsEdge(1, 0));
        Assertions.assertTrue(g.IsEdge(0, 1));
    }

    @Test
    public void removeEdgeTest() {
        SimpleGraph g = new SimpleGraph(3);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddEdge(0, 1);
        Assertions.assertTrue(g.IsEdge(1, 0));
        Assertions.assertTrue(g.IsEdge(0, 1));
        g.RemoveEdge(1, 0);
        Assertions.assertFalse(g.IsEdge(0, 1));
        Assertions.assertFalse(g.IsEdge(1, 0));
    }

}
