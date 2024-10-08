package vst.graph_visualizer.graph;

import javafx.scene.control.ScrollPane;

public class GraphPane extends ScrollPane {

    private Graph graph;

    public GraphPane(Graph graph) {
        setPannable(true);
        this.graph = graph;
        setContent(graph);
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        setContent(graph);
        this.graph = graph;
    }

}
