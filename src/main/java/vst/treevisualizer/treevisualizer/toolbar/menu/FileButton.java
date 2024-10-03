package vst.treevisualizer.treevisualizer.toolbar.menu;

import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;
import vst.treevisualizer.treevisualizer.visualizer.Visualizer;

public class FileButton extends TopMenu {

    public FileButton() {
        setText("File");
        setAlignment(Pos.CENTER);

        MenuItem importItem = new MenuItem("import");
        MenuItem exportItem = new MenuItem("export");

        exportItem.setOnAction(e -> {
            System.out.println("export");
        });

        getItems().addAll(importItem, exportItem);
        getStyleClass().add("menu-button");
    }

    @Override
    public void setVisualizer(Visualizer visualizer) {
        super.setVisualizer(visualizer);
        visualizer.export();
    }
}
