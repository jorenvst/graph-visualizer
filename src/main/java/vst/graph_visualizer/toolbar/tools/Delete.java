package vst.graph_visualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.graph_visualizer.visualizer.graph.GraphNode;

public class Delete extends Tool {

    public Delete() {
        super("/vst/sidebar/trash.png");
        setTooltip(new Tooltip("delete node/edge"));
    }

    public void apply(GraphNode node, double mouseX, double mouseY) {
        if (node == null) {
            return;
        }
        sideBar.getVisualizer().deleteNode(node);
    }

}
