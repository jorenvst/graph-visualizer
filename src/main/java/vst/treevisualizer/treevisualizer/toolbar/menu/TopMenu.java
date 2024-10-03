package vst.treevisualizer.treevisualizer.toolbar.menu;

import javafx.scene.control.MenuButton;
import vst.treevisualizer.treevisualizer.visualizer.Visualizer;

public abstract class TopMenu extends MenuButton {

    protected Visualizer visualizer;

    public TopMenu() {

    }

    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }
}
