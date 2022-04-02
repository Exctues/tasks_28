package task20;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null
    public int totalNodesInSubtree = 0; // общее кол-во в этом саб-дереве, включая эту вершину

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}


class SimpleTree<T> {
    class SimpleTreeEdge<T> {
        public SimpleTreeNode<T> Parent;
        public SimpleTreeNode<T> Child;

        public SimpleTreeEdge(SimpleTreeNode<T> parent, SimpleTreeNode<T> child) {
            Parent = parent;
            Child = child;
        }
    }

    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    // edges are constructed in bfs order.
    private ArrayList<SimpleTreeEdge<T>> GetAllEdges(SimpleTreeNode<T> node) {
        ArrayList<SimpleTreeEdge<T>> edges = new ArrayList<>();
        if (node == null)
            return edges;

        if (node.Children != null)
            for (SimpleTreeNode<T> x : node.Children)
                edges.addAll(GetAllEdges(x));

        // non-Root
        if (node.Parent != null)
            edges.add(new SimpleTreeEdge<T>(node.Parent, node));


        return edges;
    }

    private boolean isEven(int x) {
        return x % 2 == 0;
    }

    private boolean isCutFine(SimpleTreeEdge<T> edge) {
        return isEven(Root.totalNodesInSubtree - edge.Child.totalNodesInSubtree) && isEven(edge.Child.totalNodesInSubtree);
    }

    /// first node expected to be parent of cut-edge
    private void UpdateCountAfterCut(SimpleTreeNode<T> node, int lost) {
        if (node == null)
            return;
        node.totalNodesInSubtree -= lost;
        UpdateCountAfterCut(node.Parent, lost);
    }

    private void RemoveEdge(SimpleTreeEdge<T> edge) {
        edge.Child.Parent = null;
        edge.Parent.Children = edge.Parent.Children.stream().filter(e -> e != edge.Child).collect(Collectors.toList());
    }

    private void UpdateTotalNodesInSubtree(SimpleTreeNode<T> node) {
        if (node == null)
            return;
        node.totalNodesInSubtree = 1;
        if (node.Children != null) {
            for (SimpleTreeNode<T> child : node.Children) {
                UpdateTotalNodesInSubtree(child);
                node.totalNodesInSubtree += child.totalNodesInSubtree;
            }
        }
    }

    public ArrayList<T> EvenTrees() {
        UpdateTotalNodesInSubtree(Root);

        ArrayList<SimpleTreeEdge<T>> edges = GetAllEdges(Root);

        ArrayList<T> keysToRemove = new ArrayList<>();
        for (int i = edges.size() - 1; i >= 0; i--) {
            SimpleTreeEdge<T> edge = edges.get(i);
            if (isCutFine(edge)) {
//                we don't want to modify tree itself, so it is commented
//                RemoveEdge(edge);
                UpdateCountAfterCut(edge.Parent, edge.Child.totalNodesInSubtree);
                keysToRemove.add(edge.Parent.NodeValue);
                keysToRemove.add(edge.Child.NodeValue);
            }
        }
        return keysToRemove;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null) ParentNode.Children = new ArrayList<>();
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete == Root) Root = null;
        if (NodeToDelete.Parent != null && NodeToDelete.Parent.Children != null)
            NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Children = null;
    }

    private List<SimpleTreeNode<T>> GetAllNodes(SimpleTreeNode<T> node) {
        if (node == null) return new ArrayList<>();

        ArrayList<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (node.Children != null) for (int i = 0; i < node.Children.size(); i++)
            nodes.addAll(GetAllNodes(node.Children.get(i)));

        nodes.add(node);

        return nodes;
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        return GetAllNodes(Root);
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        return GetAllNodes(Root).stream().filter(e -> e.NodeValue.equals(val)).collect(Collectors.toList());
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        if (OriginalNode.Parent != null) {
            OriginalNode.Parent.Children.remove(OriginalNode);
            if (OriginalNode.Parent.Children.size() == 0) OriginalNode.Parent.Children = null;
        }

        if (OriginalNode.Children != null) {
            OriginalNode.Children.remove(NewParent);
        }

        AddChild(NewParent, OriginalNode);

        if (OriginalNode == Root) Root = null;
    }

    public int Count() {
        return GetAllNodes(Root).size();
    }

    public int LeafCount() {
        return (int) GetAllNodes(Root).stream().filter(n -> n.Children == null || n.Children.size() == 0).count();
    }
}

