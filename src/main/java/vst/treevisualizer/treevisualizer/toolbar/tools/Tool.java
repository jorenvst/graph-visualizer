package vst.treevisualizer.treevisualizer.toolbar.tools;

import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import vst.treevisualizer.Main;
import vst.treevisualizer.treevisualizer.toolbar.SideBar;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

import java.util.ArrayList;
import java.util.List;

public abstract class Tool extends ToggleButton {

    protected SideBar sideBar;
    protected List<TreeNode> selectedNodes = new ArrayList<>();

    public Tool(String image) {
        ImageView symbol = new ImageView(Main.class.getResource(image).toExternalForm());
        symbol.setPreserveRatio(true);
        symbol.setFitWidth(40);

        setGraphic(symbol);
        getStyleClass().add("tool");
    }

    public void setSideBar(SideBar sideBar) {
        this.sideBar = sideBar;
        selectedProperty().addListener(o -> {
            if (selectedProperty().get()) {
                sideBar.select(this);
            } else if (sideBar.selectedToolProperty().get().equals(this)) {
                selectedProperty().set(true);
            }
        });
    }

    public void clearSelectedNodes() {
        selectedNodes.clear();
    }

    public abstract void apply(TreeNode node, double mouseX, double mouseY);
}
