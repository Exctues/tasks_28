package task21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class DFSTest {
    @Test
    public void dfsTest1() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddEdge(0, 2);
        g.AddEdge(2, 1);
        g.AddEdge(1, 3);

        ArrayList<Vertex> res = g.DepthFirstSearch(0, 3);

        Assertions.assertEquals(4, res.size());
        Assertions.assertEquals(0, res.get(0).Value);
        Assertions.assertEquals(2, res.get(1).Value);
        Assertions.assertEquals(1, res.get(2).Value);
        Assertions.assertEquals(3, res.get(3).Value);
    }

    @Test
    public void dfsTest2() {
        SimpleGraph g = new SimpleGraph(4);
        g.AddVertex(0);
        g.AddVertex(1);
        g.AddVertex(2);
        g.AddVertex(3);
        g.AddEdge(0, 2);
        g.AddEdge(2, 1);
        g.AddEdge(1, 3);

        ArrayList<Vertex> res = g.DepthFirstSearch(3, 3);

        Assertions.assertEquals(0, res.size());

    }
}
