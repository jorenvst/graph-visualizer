package vst.graph_visualizer.controller;

import vst.graph_visualizer.model.Graph;
import vst.graph_visualizer.view.GraphView;
import vst.graph_visualizer.view.ToolBarView;

public class GraphController {

    // model
    private Graph graph;

    // views
    private final GraphView graphView;
    private final ToolBarView toolBarView;

    public GraphController() {
        this.graphView = new GraphView();
        this.toolBarView = new ToolBarView();

        graph.addListener(graphView);
        graph.addListener(toolBarView);
    }
}
