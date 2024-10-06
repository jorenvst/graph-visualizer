package vst.graph_visualizer.tools;

import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Tool extends ToggleButton {

    // currying to handle parameters
    protected final Map<EventType<MouseEvent>, Function<Graph, Function<Node, Consumer<Coordinate>>>> events;

    public Tool(String image, String tooltip) {
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource(image)).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);

        setTooltip(new Tooltip(tooltip));
        setGraphic(imageView);

        events = new HashMap<>();
    }

    public void apply(MouseEvent event, Graph graph, Node source, Coordinate pos) {
        Function<Graph, Function<Node, Consumer<Coordinate>>> func = events.get(event.getEventType());
        if (func != null) {
            func.apply(graph).apply(source).accept(pos);
        }
    }
}
