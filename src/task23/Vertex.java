package task23;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

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


    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        if (VFrom == VTo)
            return new ArrayList<>();

        //  init
        Queue<Integer> queue = new ArrayDeque<>();
        int[] nodePathParent = new int[vertex.length];
        for (int i = 0; i < vertex.length; i++) {
            vertex[i].Hit = false;
            nodePathParent[i] = -1;
        }

        queue.offer(VFrom);
        vertex[VFrom].Hit = true;

        // bfs
        while (!queue.isEmpty()) {
            int v = queue.poll();

            if (m_adjacency[v][VTo] > 0) {
                nodePathParent[VTo] = v;
                return constructPath(nodePathParent, VTo);
            }


            for (int i = 0; i < m_adjacency[v].length; i++) {
                if (IsEdge(v, i) && !vertex[i].Hit) {
                    queue.offer(i);
                    nodePathParent[i] = v;
                    vertex[i].Hit = true;
                }
            }
        }

        return new ArrayList<>();
    }

    ArrayList<Integer> GetAllNeighbours(int v) {
        ArrayList<Integer> neighbours = new ArrayList<>();
        for (int i = 0; i < m_adjacency[v].length; i++)
            if (IsEdge(v, i) && v != i)
                neighbours.add(i);
        return neighbours;
    }

    boolean IsConnectionBetweenAnyVertices(ArrayList<Integer> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size(); j++) {
                int v1 = vertices.get(i);
                int v2 = vertices.get(j);
                if (IsEdge(v1, v2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Vertex> WeakVertices() {
        ArrayList<Vertex> weakVertices = new ArrayList<>();
        for (int v = 0; v < vertex.length; v++)
            if (!IsConnectionBetweenAnyVertices(GetAllNeighbours(v)))
                weakVertices.add(vertex[v]);
        return weakVertices;
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
        // ?????? ?????? ???????????????????? ?????????? ??????????????
        // ?? ?????????????????? value
        // ?? ?????????????????? ?????????????? vertex

        final int pos = getNewVertexPosition();
        if (pos == -1)
            return;

        vertex[pos] = new Vertex(value);
        for (int i = 0; i < max_vertex; i++)
            RemoveEdge(pos, i);

    }

    // ?????????? ?? ??????????, ?????????????????? v -- ???????????? ??????????????
    // ?? ????????????  vertex
    public void RemoveVertex(int v) {
        // ?????? ?????? ???????????????? ?????????????? ???? ?????????? ???? ??????????????
        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++)
            RemoveEdge(v, i);

    }

    public boolean IsEdge(int v1, int v2) {
        // true ???????? ???????? ?????????? ?????????? ?????????????????? v1 ?? v2
        return m_adjacency[v1][v2] > 0;
    }

    public void AddEdge(int v1, int v2) {
        // ???????????????????? ?????????? ?????????? ?????????????????? v1 ?? v2
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        // ???????????????? ?????????? ?????????? ?????????????????? v1 ?? v2
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }
}
