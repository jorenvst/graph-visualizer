package vst.treevisualizer.treevisualizer.toolbar.menu;

import javafx.geometry.Pos;
import javafx.scene.control.MenuItem;

public class FileButton extends TopMenu {

    public FileButton() {
        setText("File");
        setAlignment(Pos.CENTER);
        getItems().addAll(new MenuItem("import"), new MenuItem("export"));
        getStyleClass().add("menu-button");
    }

}
