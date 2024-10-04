package vst.graph_visualizer.toolbar.menu;

import javafx.scene.control.MenuButton;
import vst.graph_visualizer.visualizer.Visualizer;

public abstract class TopMenu extends MenuButton {

    protected Visualizer visualizer;

    public TopMenu() {

    }

    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }
}
