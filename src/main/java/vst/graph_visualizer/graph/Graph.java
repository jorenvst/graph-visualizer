package vst.graph_visualizer.graph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.collections.SetChangeListener.Change;
import javafx.scene.layout.Pane;

public class Graph extends Pane {

    private final ObservableSet<Vertex> vertices;
    private final ObservableSet<Edge> edges;

    public Graph() {
        this.vertices = FXCollections.observableSet();
        this.edges = FXCollections.observableSet();

        vertices.addListener((SetChangeListener<? super Vertex>) this::updateVertices);
        edges.addListener((SetChangeListener<? super Edge>) this::updateEdges);
    }

    private void updateVertices(Change<? extends Vertex> change) {
        Vertex vertex;
        if (change.wasAdded()) {
            vertex = change.getElementAdded();
            this.getChildren().add(vertex);
        } else if (change.wasRemoved()) {
            vertex = change.getElementRemoved();
            this.getChildren().remove(vertex);
            this.edges.removeAll(vertex.getEdges());
        }
    }

    private void updateEdges(Change<? extends Edge> change) {
        Edge edge;
        if (change.wasAdded()) {
            edge = change.getElementAdded();
            this.getChildren().addFirst(edge);
        } else if (change.wasRemoved()) {
            edge = change.getElementRemoved();
            this.getChildren().remove(edge);
            edge.getStartVertex().removeEdge(edge);
            edge.getEndVertex().removeEdge(edge);
        }
    }

    public ObservableSet<Vertex> getVertices() {
        return vertices;
    }

    public ObservableSet<Edge> getEdges() {
        return edges;
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeVertex(Vertex vertex) {
        vertices.remove(vertex);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }
}
