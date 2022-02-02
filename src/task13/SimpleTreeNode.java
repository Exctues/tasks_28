package task13;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleTreeNode<T> {
    public T NodeValue; // значение в узле
    public SimpleTreeNode<T> Parent; // родитель или null для корня
    public List<SimpleTreeNode<T>> Children; // список дочерних узлов или null

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root; // корень, может быть null

    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null)
            ParentNode.Children = new ArrayList<>();
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if(NodeToDelete == Root)
            Root = null;
        if (NodeToDelete.Parent != null && NodeToDelete.Parent.Children != null)
            NodeToDelete.Parent.Children.remove(NodeToDelete);
        NodeToDelete.Children = null;
    }

    private List<SimpleTreeNode<T>> GetAllNodes(SimpleTreeNode<T> node) {
        if (node == null)
            return new ArrayList<>();

        ArrayList<SimpleTreeNode<T>> nodes = new ArrayList<>();
        if (node.Children != null)
            for (int i = 0; i < node.Children.size(); i++)
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
            if (OriginalNode.Parent.Children.size() == 0)
                OriginalNode.Parent.Children = null;
        }

        if(OriginalNode.Children != null) {
            OriginalNode.Children.remove(NewParent);
        }

        AddChild(NewParent, OriginalNode);

        if(OriginalNode == Root)
            Root = null;
    }

    public int Count() {
        return GetAllNodes(Root).size();
    }

    public int LeafCount() {
        return (int) GetAllNodes(Root).stream().filter(n -> n.Children == null || n.Children.size() == 0).count();
    }
}

