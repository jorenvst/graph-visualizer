package vst.treevisualizer.treevisualizer.toolbar.tools;

import vst.treevisualizer.treevisualizer.dialog.KeyDialog;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class AddNode extends Tool {

    public AddNode() {
        super("/vst/sidebar/plus.png");
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node != null) {
            return;
        }
        KeyDialog dialog = new KeyDialog(getScene().getWindow());
        dialog.showAndWait();
        Integer key = dialog.getKey();
        if (key != null && !sideBar.getVisualizer().containsNode(key)) {
            sideBar.getVisualizer().addNode(key, mouseX, mouseY);
        }
    }

}
