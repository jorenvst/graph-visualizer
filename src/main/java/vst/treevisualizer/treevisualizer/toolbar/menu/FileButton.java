package vst.treevisualizer.treevisualizer.toolbar.menu;

import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import vst.treevisualizer.treevisualizer.visualizer.Visualizer;

public class FileButton extends TopMenu {

    private final MenuItem exportItem;
    private final MenuItem importItem;

    public FileButton() {
        setText("File");
        setAlignment(Pos.CENTER);

        importItem = new MenuItem("import");
        exportItem = new MenuItem("export");

        getItems().addAll(importItem, exportItem);
        getStyleClass().add("menu-button");
    }

    @Override
    public void setVisualizer(Visualizer visualizer) {
        super.setVisualizer(visualizer);
        exportItem.setOnAction(e -> visualizer.export());
    }
}
