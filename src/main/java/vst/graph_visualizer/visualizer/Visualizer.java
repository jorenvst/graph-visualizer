package vst.graph_visualizer.visualizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import vst.graph_visualizer.toolbar.SideBar;
import vst.graph_visualizer.toolbar.TopBar;
import vst.graph_visualizer.visualizer.graph.Edge;
import vst.graph_visualizer.visualizer.graph.Graph;
import vst.graph_visualizer.visualizer.graph.GraphNode;

import java.io.File;
import java.io.IOException;

public class Visualizer extends Pane {

    private final SideBar sideBar;

    private final Graph graph;

    public Visualizer(SideBar sideBar, TopBar topBar) {
        this.sideBar = sideBar;
        setOnMousePressed(e -> sideBar.selectedToolProperty().get().apply(null, e.getX(), e.getY()));

        graph = new Graph();
    }

    public void addNode(int key, double x, double y) {
        GraphNode node = new GraphNode(key, this);
        getChildren().add(node);
        graph.addNode(node);
        node.setPos(x, y);
    }

    public void deleteNode(GraphNode node) {
        getChildren().remove(node);
        getChildren().removeAll(node.getEdges());
        graph.getNodes().remove(node);
    }

    public void addEdge(GraphNode node1, GraphNode node2) {
        // make sure nodes are different and there's no existing edges between them
        if (node1.equals(node2) || node1.getEdges().stream().anyMatch(e -> e.node1().equals(node2) || e.node2().equals(node2))) {
            return;
        }
        Edge edge = new Edge(node1, node2, this);
        getChildren().addFirst(edge);
        graph.addEdge(edge);
    }

    public void deleteEdge(Edge edge) {
        getChildren().remove(edge);
    }

    public boolean containsNode(int key) {
        return graph.getNodes().stream().anyMatch(n -> n.getKey() == key);
    }

    public SideBar getSideBar() {
        return sideBar;
    }

    public void exportGraph() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(GraphNode.class, new GraphNode.GraphNodeSerializer(GraphNode.class));
            module.addSerializer(Edge.class, new Edge.EdgeSerializer(Edge.class));
            mapper.registerModule(module);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new FileChooser().showSaveDialog(getScene().getWindow()), graph);
        } catch (IOException e) {
            throw new RuntimeException("unable to export", e);
        }
    }

    public void importGraph(File file) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.readTree(file);
        } catch (IOException e) {
            throw new RuntimeException("unable to import", e);
        }
    }
}
