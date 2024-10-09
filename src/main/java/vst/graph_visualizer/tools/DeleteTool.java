package vst.graph_visualizer.tools;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class DeleteTool extends Tool {

    public DeleteTool() {
        super("/vst/sidebar/trash.png", "delete vertex/edge");
    }

    @Override
    public void apply(EventType<? extends MouseEvent> eventType, Graph graph, Vertex vertex, Coordinate pos) {
        if (eventType == MouseEvent.MOUSE_CLICKED) {
            graph.removeVertex(vertex);
        }
    }

    @Override
    public void apply(EventType<? extends MouseEvent> eventType, Graph graph, Edge edge, Coordinate pos) {
        if (eventType == MouseEvent.MOUSE_CLICKED) {
            graph.removeEdge(edge);
        }
    }
}
