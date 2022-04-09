package task21;

import java.util.*;

class Vertex {
    public int Value;
    public boolean Hit;

    public Vertex(int val) {
        Value = val;
        Hit = false;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;

    private ArrayList<Vertex> constructPath(int[] nodePathParent, int VTo) {
        Stack<Integer> reversedPath = new Stack<>();
        int currentNode = VTo;
        while (currentNode != -1) {
            reversedPath.push(currentNode);
            currentNode = nodePathParent[currentNode];
        }

        // we actually can reserve space of list beforehand.
        ArrayList<Vertex> path = new ArrayList<>();
        while (!reversedPath.isEmpty())
            path.add(vertex[reversedPath.pop()]);

        return path;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        if (VFrom == VTo)
            return new ArrayList<>();

        //  init
        Stack<Integer> stack = new Stack<>();
        int[] nodePathParent = new int[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i].Hit = false;
            nodePathParent[i] = -1;
        }

        stack.push(VFrom);
        vertex[VFrom].Hit = true;

        // dfs
        while (!stack.empty()) {
            int v = stack.pop();

            if (m_adjacency[v][VTo] > 0){
                nodePathParent[VTo] = v;
                return constructPath(nodePathParent, VTo);
            }


            for (int i = 0; i < m_adjacency[v].length; i++) {
                if (IsEdge(v, i) && !vertex[i].Hit) {
                    stack.push(i);
                    nodePathParent[i] = v;
                    vertex[i].Hit = true;
                }
            }
        }

        return new ArrayList<>();
    }

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    private int getNewVertexPosition() {
        for (int i = 0; i < max_vertex; i++)
            if (vertex[i] == null)
                return i;
        return -1;
    }

    public void AddVertex(int value) {
        // ваш код добавления новой вершины
        // с значением value
        // в незанятую позицию vertex

        final int pos = getNewVertexPosition();
        if (pos == -1)
            return;

        vertex[pos] = new Vertex(value);
        for (int i = 0; i < max_vertex; i++)
            RemoveEdge(pos, i);

    }

    // здесь и далее, параметры v -- индекс вершины
    // в списке  vertex
    public void RemoveVertex(int v) {
        // ваш код удаления вершины со всеми её рёбрами
        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++)
            RemoveEdge(v, i);

    }

    public boolean IsEdge(int v1, int v2) {
        // true если есть ребро между вершинами v1 и v2
        return m_adjacency[v1][v2] > 0;
    }

    public void AddEdge(int v1, int v2) {
        // добавление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // удаление ребра между вершинами v1 и v2
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }
}
