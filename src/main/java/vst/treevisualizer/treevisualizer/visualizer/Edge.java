package vst.treevisualizer.treevisualizer.visualizer;

import javafx.scene.shape.Line;
import vst.treevisualizer.treevisualizer.toolbar.Delete;

public class Edge extends Line implements TreeObject {

    private Visualizer visualizer;

    public Edge(TreeNode node1, TreeNode node2, Visualizer visualizer) {
        this.visualizer = visualizer;

        node1.addEdge(this);
        node2.addEdge(this);

        startXProperty().bind(node1.centerX());
        startYProperty().bind(node1.centerY());
        endXProperty().bind(node2.centerX());
        endYProperty().bind(node2.centerY());

        getStyleClass().add("edge");

        // TODO: improve
        setOnMouseClicked(e -> {
            if (visualizer.getToolBar().selectedToolProperty().get() instanceof Delete) {
                visualizer.deleteEdge(this);
            }
        });
    }
}
