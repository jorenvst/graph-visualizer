package vst.treevisualizer.treevisualizer.toolbar;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import vst.treevisualizer.treevisualizer.TreeVisualizer;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

import java.util.ArrayList;
import java.util.List;

public abstract class Tool extends ToggleButton {

    protected ToolBar toolBar;
    protected List<TreeNode> selectedNodes = new ArrayList<>();

    public Tool(String image) {
        ImageView symbol = new ImageView(TreeVisualizer.class.getResource(image).toExternalForm());
        symbol.setPreserveRatio(true);
        symbol.setFitWidth(40);

        setGraphic(symbol);
        getStyleClass().add("tool");
    }

    public void setToolBar(ToolBar toolBar) {
        this.toolBar = toolBar;
        selectedProperty().addListener(o -> {
            if (selectedProperty().get()) {
                toolBar.select(this);
            } else if (toolBar.selectedToolProperty().get().equals(this)) {
                selectedProperty().set(true);
            }
        });
    }

    public void clearSelectedNodes() {
        selectedNodes.clear();
    }

    public abstract void apply(TreeNode node, double mouseX, double mouseY);
}
