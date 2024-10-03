package vst.treevisualizer.visualizer.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

    Set<GraphNode> nodes;
    Set<Edge> edges;

    public Graph() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public void addNode(GraphNode node) {
        nodes.add(node);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    // getters and setters for (de)serialization
    public Set<GraphNode> getNodes() {
        return nodes;
    }

    public void setNodes(Set<GraphNode> nodes) {
        this.nodes = nodes;
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public void setEdges(Set<Edge> edges) {
        this.edges = edges;
    }
}
