package vst.treevisualizer.treevisualizer.toolbar;

import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class Delete extends Tool {

    public Delete() {
        super("/vst/toolbar/trash.png");
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node != null) {
            toolBar.getVisualizer().deleteNode(node);
        }
    }

}
