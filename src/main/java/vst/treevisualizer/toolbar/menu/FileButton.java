package vst.treevisualizer.toolbar.menu;

import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import vst.treevisualizer.visualizer.Visualizer;

import java.io.File;

public class FileButton extends TopMenu {

    private final MenuItem exportGraph;
    private final MenuItem importGraph;

    public FileButton() {
        setText("File");
        setAlignment(Pos.CENTER);

        importGraph = new MenuItem("import");
        exportGraph = new MenuItem("export");

        getItems().addAll(importGraph, exportGraph);
        getStyleClass().add("menu-button");
    }

    @Override
    public void setVisualizer(Visualizer visualizer) {
        super.setVisualizer(visualizer);
        exportGraph.setOnAction(e -> visualizer.exportGraph());
        importGraph.setOnAction(e -> {
            FileChooser chooser = new FileChooser();
            File file = chooser.showOpenDialog(getScene().getWindow());
            if (file != null) {
                visualizer.importGraph(file);
            }
        });
    }
}
