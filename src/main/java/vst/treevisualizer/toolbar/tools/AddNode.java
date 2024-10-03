package vst.treevisualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.treevisualizer.dialog.KeyDialog;
import vst.treevisualizer.visualizer.graph.GraphNode;

public class AddNode extends Tool {

    public AddNode() {
        super("/vst/sidebar/plus.png");
        setTooltip(new Tooltip("add node"));
    }

    public void apply(GraphNode node, double mouseX, double mouseY) {
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
