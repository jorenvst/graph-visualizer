package vst.graph_visualizer.tools;

import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class AddEdgeTool extends Tool {

    public AddEdgeTool() {
        super("/vst/sidebar/line.png", "add edge");
    }

    @Override
    public void apply(MouseEvent event, Graph graph, Vertex vertex, Coordinate pos) {
        if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
            if (graph.getSelectedVertices().contains(vertex)) {
                graph.deselectVertex(vertex);
            } else {
                graph.selectVertex(vertex);
            }

            if (graph.getSelectedVertices().size() == 2) {
                Vertex v1 = graph.getSelectedVertex(0);
                Vertex v2 = graph.getSelectedVertex(1);
                // check if there's no existing vertex between the two vertices
                if (v1.getEdges().stream().noneMatch(e -> e.v2().equals(v2))) {
                    graph.addEdge(new Edge(v1, v2));
                }
                graph.getSelectedVertices().clear();
            }
        }
    }
}
