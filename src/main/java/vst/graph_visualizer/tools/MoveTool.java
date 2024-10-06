package vst.graph_visualizer.tools;

import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.GraphComponent;
import vst.graph_visualizer.graph.Vertex;

public class MoveTool extends Tool {

    private final Coordinate drag;

    public MoveTool() {
        super("/vst/sidebar/move.png", "move vertex");
        events.put(MouseEvent.MOUSE_DRAGGED, g -> c -> p -> onDrag(c, p));
        events.put(MouseEvent.MOUSE_PRESSED, g -> c -> p -> onPress(c, p));
        events.put(MouseEvent.MOUSE_RELEASED, g -> c -> p -> onRelease(c));

        drag = new Coordinate(0, 0);
    }

    private void onPress(GraphComponent component, Coordinate pos) {
        if (!(component instanceof Vertex v)) {
            return;
        }
        // problem with dragging outside viewport and panning
        drag.xProperty().set(pos.x() - v.getCenter().x());
        drag.yProperty().set(pos.y() - v.getCenter().y());
        v.setCursor(Cursor.MOVE);
    }

    private void onDrag(GraphComponent component, Coordinate pos) {
        if (!(component instanceof Vertex v)) {
            return;
        }
        v.getCenter().xProperty().set(pos.x() - drag.x());
        v.getCenter().yProperty().set(pos.y() - drag.y());
    }

    private void onRelease(GraphComponent component) {
        if (!(component instanceof Vertex v)) {
            return;
        }
        drag.set(0, 0);
        v.setCursor(Cursor.DEFAULT);
    }
}
