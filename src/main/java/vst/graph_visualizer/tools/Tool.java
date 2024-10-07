package vst.graph_visualizer.tools;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import vst.graph_visualizer.graph.*;

import java.util.Objects;

public abstract class Tool extends ToggleButton {

    public Tool(String image, String tooltip) {
        ImageView imageView = new ImageView(Objects.requireNonNull(getClass().getResource(image)).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);

        setTooltip(new Tooltip(tooltip));
        setGraphic(imageView);
    }

    public void apply(MouseEvent event, Graph graph, GraphPane pane, Coordinate pos) {

    }

    public void apply(MouseEvent event, Graph graph, Vertex vertex, Coordinate pos) {

    }

    public void apply(MouseEvent event, Graph graph, Edge edge, Coordinate pos) {

    }
}
