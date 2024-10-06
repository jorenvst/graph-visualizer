package vst.graph_visualizer.tools;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class AddEdgeTool extends Tool {

    public AddEdgeTool() {
        super("/vst/sidebar/line.png", "add edge");
        events.put(MouseEvent.MOUSE_CLICKED, g -> c -> p -> onClick(g, c));
        events.put(MouseEvent.MOUSE_ENTERED, g -> c -> p -> onEnter(c));
        events.put(MouseEvent.MOUSE_EXITED, g -> c -> p -> onExit(c));
    }

    private void onEnter(Node source) {
        if (!(source instanceof Vertex v)) {
            return;
        }
        v.setCursor(Cursor.HAND);
    }

    private void onExit(Node source) {
        if (!(source instanceof Vertex v)) {
            return;
        }
        v.setCursor(Cursor.DEFAULT);
    }

    private void onClick(Graph graph, Node source) {
        if (!(source instanceof Vertex v)) {
            return;
        }

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
