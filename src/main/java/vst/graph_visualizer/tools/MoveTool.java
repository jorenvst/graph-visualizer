package vst.graph_visualizer.tools;

import javafx.scene.Cursor;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;
import vst.graph_visualizer.graph.Vertex;

public class MoveTool extends Tool {

    public MoveTool() {
        super("/vst/sidebar/move.png", "move vertex");
    }

    @Override
    public void apply(Graph graph, GraphComponent component, Coordinate pos) {
        // implemented in Vertex
    }
}
