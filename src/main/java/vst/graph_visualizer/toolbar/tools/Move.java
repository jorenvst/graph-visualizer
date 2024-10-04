package vst.graph_visualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.graph_visualizer.visualizer.graph.GraphNode;

public class Move extends Tool {

    public Move() {
        super("/vst/sidebar/move.png");
        setTooltip(new Tooltip("move nodes"));
    }

    @Override
    public void apply(GraphNode node, double mouseX, double mouseY) {
        // implemented in TreeNode, movable property is bound to if this tool is selected
    }
}
