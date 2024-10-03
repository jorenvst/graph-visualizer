package vst.treevisualizer.treevisualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class Delete extends Tool {

    public Delete() {
        super("/vst/sidebar/trash.png");
        setTooltip(new Tooltip("delete node/edge"));
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node == null) {
            return;
        }
        sideBar.getVisualizer().deleteNode(node);
    }

}
