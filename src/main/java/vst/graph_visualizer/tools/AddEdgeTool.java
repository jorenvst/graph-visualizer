package vst.graph_visualizer.tools;

import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.*;

public class AddEdgeTool extends Tool {

    public AddEdgeTool() {
        super("/vst/sidebar/line.png", "add edge");
        events.put(MouseEvent.MOUSE_CLICKED, g -> c -> p -> onClick(g, c));
    }

    private void onClick(Graph graph, GraphComponent component) {
        if (component == null || component instanceof Edge) {
            return;
        }

        Vertex v = (Vertex)component;
        if (graph.getSelectedVertices().contains(v)) {
            graph.deselectVertex(v);
        } else {
            graph.selectVertex(v);
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
