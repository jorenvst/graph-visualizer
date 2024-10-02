package vst.treevisualizer.treevisualizer.toolbar;

import javafx.scene.layout.HBox;
import vst.treevisualizer.treevisualizer.toolbar.menu.Menus;

import java.util.Arrays;

public class TopBar extends HBox {

    public TopBar() {
        getChildren().addAll(Arrays.stream(Menus.values()).map(Menus::get).toList());
        getStyleClass().add("top-bar");
    }
}
