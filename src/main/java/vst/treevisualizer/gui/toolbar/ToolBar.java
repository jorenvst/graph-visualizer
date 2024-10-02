package vst.treevisualizer.gui.toolbar;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import vst.treevisualizer.gui.Visualizer;

public class ToolBar extends VBox {

    private final DrawEdgeButton drawEdgeButton = new DrawEdgeButton();
    private final DeleteNodeButton deleteNodeButton = new DeleteNodeButton();

    private final BooleanProperty selectProperty = new SimpleBooleanProperty();

    public ToolBar(Visualizer visualizer) {
        selectProperty.bind(drawEdgeButton.selectedProperty().and(deleteNodeButton.selectedProperty()));

        getChildren().add(new AddNodeButton(visualizer));
        getChildren().add(drawEdgeButton);
        getChildren().add(deleteNodeButton);

        setSpacing(15);
        setStyle("-fx-border-width: 0 4 0 0;" +
                "-fx-border-color: #404040;");
        setAlignment(Pos.CENTER);
        setPrefWidth(50);
    }

    public BooleanProperty drawEdgeProperty() {
        return drawEdgeButton.selectedProperty();
    }

    public BooleanProperty deleteNodeProperty() {
        return deleteNodeButton.selectedProperty();
    }

    public BooleanProperty selectProperty() {
        return selectProperty;
    }
}
