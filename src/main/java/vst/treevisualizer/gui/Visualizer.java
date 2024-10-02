package vst.treevisualizer.gui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import vst.treevisualizer.gui.toolbar.ToolBar;
import vst.treevisualizer.gui.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Visualizer extends Pane {

    private ToolBar toolBar;

    public Visualizer() {
        setStyle("-fx-background-color: #383838;");
    }

    public void addNode(int key) {
        TreeNode node = new TreeNode(key);

        node.selectedProperty().addListener(o -> {
            if (toolBar.drawEdgeProperty().get()) {
                List<TreeNode> selectedNodes = new ArrayList<>();
                for (Node child : getChildren()) {
                    if (child instanceof TreeNode treeNode) {
                        if (treeNode.selectedProperty().get()) {
                            selectedNodes.add(treeNode);
                        }
                    }
                }
                if (selectedNodes.size() == 2) {
                    drawLine(selectedNodes.getFirst(), selectedNodes.get(1));
                }
            } else if (toolBar.deleteNodeProperty().get()) {
                List<TreeNode> selectedNodes = new ArrayList<>();
                for (Node child : getChildren()) {
                    if (child instanceof TreeNode treeNode) {
                        if (treeNode.selectedProperty().get()) {
                            selectedNodes.add(treeNode);
                        }
                    }
                }
                getChildren().removeAll(selectedNodes);
            }
        });

        final Delta dragDelta = new Delta();
        node.setOnMousePressed(mouseEvent -> {
            if (toolBar.drawEdgeProperty().get() || toolBar.deleteNodeProperty().get()) {
                node.selectedProperty().set(!node.selectedProperty().get());
            } else {
                dragDelta.x = node.getLayoutX() - mouseEvent.getSceneX();
                dragDelta.y = node.getLayoutY() - mouseEvent.getSceneY();
            }
            System.out.println(node.getKey() + " is selected: " + node.selectedProperty().get());
        });
        node.setOnMouseDragged(mouseEvent -> {
            if (toolBar.selectProperty().get()) {

            } else {
                node.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
                node.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
            }
        });

        getChildren().add(node);
    }

    private void drawLine(TreeNode node1, TreeNode node2) {
        node1.selectedProperty().set(false);
        node2.selectedProperty().set(false);
        Line line = new Line();
        line.startXProperty().bind(node1.centerX());
        line.startYProperty().bind(node1.centerY());
        System.out.println(node1.centerX().get() + ", " + node1.centerY().get());
        line.endXProperty().bind(node2.centerX());
        line.endYProperty().bind(node2.centerY());
        System.out.println(node2.centerX().get() + ", " + node2.centerY().get());
        getChildren().add(line);
        System.out.println("drew line between " + node1.getKey() + " and " + node2.getKey());

        line.setStroke(Color.web("#0c75de"));
        line.setStrokeWidth(8);

        List<Node> treeNodes = new ArrayList<>();
        for (Node child : getChildren()) {
            if (child instanceof TreeNode) {
                treeNodes.add(child);
            }
        }
        getChildren().removeAll(treeNodes);
        getChildren().addAll(treeNodes);

        line.setOnMouseClicked(e -> {
            if (toolBar.deleteNodeProperty().get()) {
                getChildren().remove(line);
            }
        });
    }

    public boolean containsNode(int key) {
        return getChildren().stream().anyMatch(n -> {
            if (n instanceof TreeNode) {
                return ((TreeNode) n).getKey() == key;
            } else {
                return false;
            }
        });
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
        toolBar.drawEdgeProperty().addListener(o -> getChildren().forEach(n -> {
            if (n instanceof TreeNode treeNode) {
                treeNode.selectedProperty().set(false);
            }
        }));
    }

    static class Delta { double x, y; }
}
