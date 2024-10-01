package vst.treevisualizer.gui.toolbar;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import vst.treevisualizer.gui.Visualizer;

public class ToolBar extends VBox {

    public ToolBar(Visualizer visualizer) {
        getChildren().add(new addNodeButton(visualizer));

        setStyle("-fx-border-width: 0 4 0 0;" +
                "-fx-border-color: #404040;");
        setAlignment(Pos.CENTER);
        setPrefWidth(50);
    }
}
