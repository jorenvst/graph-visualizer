package vst.graph_visualizer.view;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import vst.graph_visualizer.model.Graph;

public class GraphView extends ScrollPane implements InvalidationListener {

    private final Pane graphPane;
    private Graph graph;

    public GraphView() {
        graphPane = new Pane();
    }

    @Override
    public void invalidated(Observable observable) {

    }
}
