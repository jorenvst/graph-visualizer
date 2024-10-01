package vst.treevisualizer.gui;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import vst.treevisualizer.gui.tree.TreeNode;

public class Layer extends GridPane {

    private final int cols;

    public Layer(int depth) {
        this.cols = (int)Math.pow(2, depth);

        for (int i = 0; i < cols; i++) {
            ColumnConstraints colConst = new ColumnConstraints();
            colConst.setPercentWidth(100.0 / cols);
            getColumnConstraints().add(colConst);
        }

        RowConstraints rowConst = new RowConstraints();
        rowConst.setPercentHeight(100.0);
        getRowConstraints().add(rowConst);
    }

    public boolean isFull() {
        return getChildren().size() == cols;
    }

    public void addNode(int key) {
        add(new TreeNode(key), getChildren().size(), 0);
    }

    public boolean containsKey(int key) {
        return getChildren().stream().anyMatch(n -> ((TreeNode)n).getKey() == key);
    }
}
