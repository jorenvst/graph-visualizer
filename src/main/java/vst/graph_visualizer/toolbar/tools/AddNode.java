package vst.graph_visualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.graph_visualizer.dialog.KeyDialog;
import vst.graph_visualizer.visualizer.graph.GraphNode;

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
