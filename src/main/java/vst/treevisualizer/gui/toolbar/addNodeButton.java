package vst.treevisualizer.gui.toolbar;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import vst.treevisualizer.gui.Visualizer;

public class addNodeButton extends Button {

    public addNodeButton(Visualizer visualizer) {
        ImageView addNodeGraphic = new ImageView(getClass().getResource("/vst/addNodeButton/plus-icon.png").toExternalForm());
        addNodeGraphic.setPreserveRatio(true);
        addNodeGraphic.setFitWidth(40);

        setGraphic(addNodeGraphic);
        setStyle("-fx-background-color: transparent");

        Tooltip tooltip = new Tooltip();
        tooltip.setText("add tree node");
        setTooltip(tooltip);
        setOnMouseClicked(e -> {
            new KeyPopUp(visualizer).showAndWait();
        });
    }

}
