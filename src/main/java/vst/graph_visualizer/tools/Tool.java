package vst.graph_visualizer.tools;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphComponent;

import java.util.Objects;

public abstract class Tool extends ToggleButton {

    public Tool(String image, String tooltip) {
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource(image)).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);

        setTooltip(new Tooltip(tooltip));
        setGraphic(imageView);
    }

    public abstract void apply(Graph graph, GraphComponent component, Coordinate pos);
}
