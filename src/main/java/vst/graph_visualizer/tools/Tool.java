package vst.graph_visualizer.tools;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public enum Tool {

    ADD_VERTEX("/vst/sidebar/plus.png", "add vertex"),
    ADD_EDGE("/vst/sidebar/line.png", "add edge"),
    MOVE("/vst/sidebar/move.png", "move vertex"),
    DELETE("/vst/sidebar/trash.png", "delete vertex/edge");

    private final ToggleButton button;

    Tool(String image, String tooltip) {
        ImageView imageView = new ImageView(getClass().getResource(image).toExternalForm());
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(40);

        button = new ToggleButton();
        button.setTooltip(new Tooltip(tooltip));
        button.setGraphic(imageView);
    }

    public ToggleButton get() {
        return button;
    }
}
