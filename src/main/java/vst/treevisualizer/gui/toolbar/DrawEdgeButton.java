package vst.treevisualizer.gui.toolbar;

import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class DrawEdgeButton extends ToggleButton {

    public DrawEdgeButton() {
        ImageView addEdgeGraphic = new ImageView(getClass().getResource("/vst/toolbar/line.png").toExternalForm());
        addEdgeGraphic.setPreserveRatio(true);
        addEdgeGraphic.setFitWidth(40);

        setGraphic(addEdgeGraphic);
        setStyle("-fx-background-color: transparent");

        Tooltip tooltip = new Tooltip();
        tooltip.setText("draw edge mode");
        setTooltip(tooltip);
        setOnMouseClicked(e -> {
            if (isSelected()) {
                setStyle("-fx-background-color: #505050");
            } else {
                setStyle("-fx-background-color: transparent");
            }
        });
    }
}
