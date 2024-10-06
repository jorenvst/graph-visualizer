package vst.graph_visualizer.tools;

import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.dialog.KeyDialog;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class AddVertexTool extends Tool {

    public AddVertexTool() {
        super("/vst/sidebar/plus.png", "add vertex");
        events.put(MouseEvent.MOUSE_CLICKED, g -> c -> p -> onClick(g, c, p));
    }

    private void onClick(Graph graph, Node source, Coordinate pos) {
        if (source instanceof Vertex || source instanceof Edge) {
            return;
        }
        KeyDialog dialog = new KeyDialog(getScene().getWindow());
        dialog.showAndWait();

        Integer key = dialog.getKey();
        if (key != null && graph.getVertices().stream().noneMatch(v -> v.getKey() == key)) {
            graph.addVertex(new Vertex(key, pos.x(), pos.y()));
        }
    }
}
