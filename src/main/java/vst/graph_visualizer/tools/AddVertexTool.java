package vst.graph_visualizer.tools;

import vst.graph_visualizer.dialog.KeyDialog;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;
import vst.graph_visualizer.graph.Vertex;

public class AddVertexTool extends Tool {

    public AddVertexTool() {
        super("/vst/sidebar/plus.png", "add vertex");
    }

    @Override
    public void apply(Graph graph, GraphComponent component, Coordinate pos) {
        if (component != null) {
            return;
        }
        KeyDialog dialog = new KeyDialog(getScene().getWindow());
        dialog.showAndWait();

        Integer key = dialog.getKey();
        if (key != null && graph.getVertices().stream().noneMatch(v -> v.getKey() == key)) {
            graph.addComponent(new Vertex(key, pos.x(), pos.y()));
        }
    }
}
