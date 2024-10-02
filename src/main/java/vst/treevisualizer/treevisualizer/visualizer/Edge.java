package vst.treevisualizer.treevisualizer.visualizer;

import javafx.scene.shape.Line;
import vst.treevisualizer.treevisualizer.toolbar.tools.Tools;

public class Edge extends Line {

    private final TreeNode node1;
    private final TreeNode node2;

    public Edge(TreeNode node1, TreeNode node2, Visualizer visualizer) {
        this.node1 = node1;
        this.node2 = node2;
        node1.addEdge(this);
        node2.addEdge(this);

        startXProperty().bind(node1.centerX());
        startYProperty().bind(node1.centerY());
        endXProperty().bind(node2.centerX());
        endYProperty().bind(node2.centerY());

        getStyleClass().add("edge");

        setOnMouseClicked(e -> {
        if (visualizer.getSideBar().selectedToolProperty().get().equals(Tools.DELETE.get())) {
                visualizer.deleteEdge(this);
            }
        });
    }

    public TreeNode node1() {
        return node1;
    }

    public TreeNode node2() {
        return node2;
    }
}
