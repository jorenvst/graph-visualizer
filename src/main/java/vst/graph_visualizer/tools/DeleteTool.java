package vst.graph_visualizer.tools;

import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class DeleteTool extends Tool {

    public DeleteTool() {
        super("/vst/sidebar/trash.png", "delete vertex/edge");
        events.put(MouseEvent.MOUSE_CLICKED, g -> c -> p -> onClick(g, c));
        events.put(MouseEvent.MOUSE_ENTERED, g -> c -> p -> onEnter(c));
        events.put(MouseEvent.MOUSE_EXITED, g -> c -> p -> onExit(c));
    }

    private void onEnter(Node source) {
        if (source instanceof Vertex v) {
            v.setCursor(Cursor.HAND);
        } else if (source instanceof Edge e) {
            e.setCursor(Cursor.HAND);
        }
    }

    private void onExit(Node source) {
        if (source instanceof Vertex v) {
            v.setCursor(Cursor.DEFAULT);
        } else if (source instanceof Edge e) {
            e.setCursor(Cursor.DEFAULT);
        }
    }

    private void onClick(Graph graph, Node source) {
        if (source instanceof Edge e) {
            graph.removeEdge(e);
        } else if (source instanceof Vertex v) {
            graph.removeVertex(v);
        }
    }
}
