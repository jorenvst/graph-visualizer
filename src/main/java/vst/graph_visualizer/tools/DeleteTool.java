package vst.graph_visualizer.tools;

import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;

public class DeleteTool extends Tool {

    public DeleteTool() {
        super("/vst/sidebar/trash.png", "delete vertex/edge");
    }

    @Override
    public void apply(Graph graph, GraphComponent component, Coordinate pos) {
        graph.removeComponent(component);
    }
}
