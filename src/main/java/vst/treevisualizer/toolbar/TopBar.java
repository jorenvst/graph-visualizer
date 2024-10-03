package vst.treevisualizer.toolbar;

import javafx.scene.layout.HBox;
import vst.treevisualizer.toolbar.menu.Menus;
import vst.treevisualizer.visualizer.Visualizer;

import java.util.Arrays;

public class TopBar extends HBox {

    public TopBar() {
        getChildren().addAll(Arrays.stream(Menus.values()).map(Menus::get).toList());
        getStyleClass().add("top-bar");
    }

    public void setVisualizer(Visualizer visualizer) {
        Arrays.stream(Menus.values()).forEach(m -> m.get().setVisualizer(visualizer));
    }
}
