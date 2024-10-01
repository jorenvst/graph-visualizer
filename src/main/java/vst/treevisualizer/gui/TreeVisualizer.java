package vst.treevisualizer.gui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import vst.treevisualizer.gui.toolbar.ToolBar;

public class TreeVisualizer extends BorderPane {

    public TreeVisualizer() {
        super();
        Visualizer visualizer = new Visualizer();
        ScrollPane pane = new ScrollPane(visualizer);
        pane.setStyle("-fx-background: #383838;" +
                "-fx-background-color: #404040;");
        pane.setFitToWidth(true);
        pane.setFitToHeight(true);
        setCenter(pane);
        setLeft(new ToolBar(visualizer));
        setStyle("-fx-background-color: #383838;");
    }
}
