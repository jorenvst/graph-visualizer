package vst.graph_visualizer.tools;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

public class MoveTool extends Tool {

    private final Coordinate drag;

    public MoveTool() {
        super("/vst/sidebar/move.png", "move vertex");
        drag = new Coordinate(0, 0);
    }

    // TODO: avoid if statements
    @Override
    public void apply(MouseEvent event, Graph graph, Vertex vertex, Coordinate pos) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            drag.xProperty().set(pos.x() - vertex.getCenter().x());
            drag.yProperty().set(pos.y() - vertex.getCenter().y());
            vertex.setCursor(Cursor.MOVE);
        } else if (event.getEventType() == MouseEvent.MOUSE_DRAGGED) {
            vertex.getCenter().xProperty().set(pos.x() - drag.x());
            vertex.getCenter().yProperty().set(pos.y() - drag.y());
        } else if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
            drag.set(0, 0);
            vertex.setCursor(Cursor.DEFAULT);
        }
    }
}
