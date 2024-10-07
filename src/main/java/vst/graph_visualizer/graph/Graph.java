package vst.graph_visualizer.graph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.SetChangeListener;
import javafx.collections.SetChangeListener.Change;
import javafx.scene.layout.Pane;

// 10.000 vertices max for decent performance (dragging is slow)
// vertices/edges get added instantaneously
public class Graph extends Pane {

    private final ObservableSet<Vertex> vertices;
    private final ObservableSet<Edge> edges;

    private final ObservableList<Vertex> selectedVertices;


    public Graph() {
        this.vertices = FXCollections.observableSet();
        this.edges = FXCollections.observableSet();

        this.selectedVertices = FXCollections.observableArrayList();


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
            edge.v1().removeEdge(edge);
            edge.v2().removeEdge(edge);
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

    public ObservableList<Vertex> getSelectedVertices() {
        return selectedVertices;
    }

    public void selectVertex(Vertex v) {
        selectedVertices.add(v);
    }

    public void deselectVertex(Vertex v) {
        selectedVertices.remove(v);
    }

    public Vertex getSelectedVertex(int i) {
        return selectedVertices.get(i);
    }
}
