package vst.graph_visualizer.tools;

import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;

public class DeleteTool extends Tool {

    public DeleteTool() {
        super("/vst/sidebar/trash.png", "delete vertex/edge");
        events.put(MouseEvent.MOUSE_CLICKED, g -> c -> p -> onClick(g, c));
    }

    private void onClick(Graph graph, GraphComponent component) {
        graph.removeComponent(component);
    }
}
