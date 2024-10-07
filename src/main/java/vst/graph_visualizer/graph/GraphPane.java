package vst.graph_visualizer.graph;

import javafx.scene.control.ScrollPane;

public class GraphPane extends ScrollPane {

    public GraphPane(Graph graph) {
        setPannable(true);
        setContent(graph);
    }

}
