package vst.graph_visualizer.tools;

import javafx.event.EventType;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.dialog.KeyDialog;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphPane;
import vst.graph_visualizer.graph.Vertex;

public class AddVertexTool extends Tool {

    public AddVertexTool() {
        super("/vst/sidebar/plus.png", "add vertex");
    }

    @Override
    public void apply(EventType<? extends MouseEvent> eventType, Graph graph, GraphPane pane, Coordinate pos) {
        if (eventType == MouseEvent.MOUSE_CLICKED) {
            KeyDialog dialog = new KeyDialog(getScene().getWindow());
            dialog.showAndWait();

            Integer key = dialog.getKey();
            if (key != null && graph.getVertices().stream().noneMatch(v -> v.getKey() == key)) {
                graph.addVertex(new Vertex(key, pos.x(), pos.y()));
            }
        }
    }
}
