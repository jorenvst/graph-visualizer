package vst.treevisualizer.treevisualizer.visualizer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.scene.layout.Pane;
import vst.treevisualizer.treevisualizer.toolbar.SideBar;
import vst.treevisualizer.treevisualizer.toolbar.TopBar;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Visualizer extends Pane {

    private final SideBar sideBar;

    private final Set<TreeNode> nodes;
    private final Set<Edge> edges;

    public Visualizer(SideBar sideBar, TopBar topBar) {
        this.sideBar = sideBar;
        setOnMousePressed(e -> sideBar.selectedToolProperty().get().apply(null, e.getX(), e.getY()));

        nodes = new HashSet<>();
        edges = new HashSet<>();
    }

    public void addNode(int key, double x, double y) {
        TreeNode node = new TreeNode(key, this);
        getChildren().add(node);
        nodes.add(node);
        node.setPos(x, y);
    }

    public void deleteNode(TreeNode node) {
        getChildren().remove(node);
        nodes.remove(node);
        getChildren().removeAll(node.getEdges());
    }

    // TODO: improve efficiency
    public void addEdge(TreeNode node1, TreeNode node2) {
        // make sure nodes are different and there's no existing edges between them
        if (node1.equals(node2) || node1.getEdges().stream().anyMatch(e -> e.node1().equals(node2) || e.node2().equals(node2))) {
            return;
        }
        Edge edge = new Edge(node1, node2, this);
        getChildren().addFirst(edge);
        edges.add(edge);
    }

    public void deleteEdge(Edge edge) {
        getChildren().remove(edge);
    }

    public boolean containsNode(int key) {
        return nodes.stream().anyMatch(n -> n.getKey() == key);
    }

    public SideBar getSideBar() {
        return sideBar;
    }

    public void export() {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(TreeNode.class, new TreeNode.TreeNodeSerializer(TreeNode.class));
            module.addSerializer(Edge.class, new Edge.EdgeSerializer(Edge.class));
            mapper.registerModule(module);

            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("graph.json"), new Graph(nodes, edges));
        } catch (IOException e) {
            throw new RuntimeException("unable to export", e);
        }
    }

    // TODO: extract class
    static final class Graph {
        Set<TreeNode> nodes;
        Set<Edge> edges;

        private Graph(Set<TreeNode> nodes, Set<Edge> edges) {
            this.nodes = nodes;
            this.edges = edges;
        }

        public Set<TreeNode> getNodes() {
            return nodes;
        }

        public void setNodes(Set<TreeNode> nodes) {
            this.nodes = nodes;
        }

        public Set<Edge> getEdges() {
            return edges;
        }

        public void setEdges(Set<Edge> edges) {
            this.edges = edges;
        }
    }
}
