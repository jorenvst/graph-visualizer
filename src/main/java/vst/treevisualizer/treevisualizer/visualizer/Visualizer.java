package vst.treevisualizer.treevisualizer.visualizer;

import javafx.scene.layout.Pane;
import vst.treevisualizer.treevisualizer.toolbar.ToolBar;

import java.util.HashSet;
import java.util.Set;

public class Visualizer extends Pane {

    private final ToolBar toolBar;

    private final Set<TreeNode> nodes;

    public Visualizer(ToolBar toolBar) {
        this.toolBar = toolBar;
        setOnMousePressed(e -> toolBar.selectedToolProperty().get().apply(null, e.getX(), e.getY()));

        nodes = new HashSet<>();
    }

    public void addNode(int key, double x, double y) {
        TreeNode node = new TreeNode(key, this);
        getChildren().add(node);
        nodes.add(node);
        node.setPos(x, y);

        final Delta delta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            if (node.isMovable()) {
                delta.x = node.getLayoutX() - mouseEvent.getSceneX();
                delta.y = node.getLayoutY() - mouseEvent.getSceneY();
            }
        });
        node.setOnMouseDragged(mouseEvent -> {
            if (node.isMovable()) {
                node.setLayoutX(mouseEvent.getSceneX() + delta.x);
                node.setLayoutY(mouseEvent.getSceneY() + delta.y);
            }
        });
    }

    private static class Delta {
        double x; double y;
    }

    public void deleteNode(TreeNode node) {
        getChildren().remove(node);
        nodes.remove(node);
        getChildren().removeAll(node.getEdges());
    }

    public void addEdge(TreeNode node1, TreeNode node2) {
        if (node1.equals(node2)) {
            return;
        }
        Edge edge = new Edge(node1, node2, this);
        getChildren().add(edge);
        // trick to have nodes come out on top (inefficient for large trees)
        getChildren().removeAll(nodes);
        getChildren().addAll(nodes);
    }

    public void deleteEdge(Edge edge) {
        getChildren().remove(edge);
    }

    public boolean containsNode(int key) {
        return nodes.stream().anyMatch(n -> n.getKey() == key);
    }

    public ToolBar getToolBar() {
        return toolBar;
    }
}
