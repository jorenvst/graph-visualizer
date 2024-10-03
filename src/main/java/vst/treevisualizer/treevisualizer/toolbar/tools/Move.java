package vst.treevisualizer.treevisualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class Move extends Tool {

    public Move() {
        super("/vst/sidebar/move.png");
        setTooltip(new Tooltip("move nodes"));
    }

    @Override
    public void apply(TreeNode node, double mouseX, double mouseY) {
        // implemented in TreeNode, movable property is bound to if this tool is selected
    }
}
