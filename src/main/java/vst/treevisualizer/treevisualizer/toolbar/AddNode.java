package vst.treevisualizer.treevisualizer.toolbar;

import vst.treevisualizer.treevisualizer.dialog.KeyDialog;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class AddNode extends Tool {

    public AddNode() {
        super("/vst/toolbar/plus.png");
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node == null) {
            KeyDialog dialog = new KeyDialog();
            dialog.showAndWait();
            Integer key = dialog.getKey();
            if (key != null && !toolBar.getVisualizer().containsNode(key)) {
                toolBar.getVisualizer().addNode(key, mouseX, mouseY);
            }
        }
    }

}
