package vst.graph_visualizer.model;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.util.Pair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph implements Observable {

    private final Set<Vertex> vertices;
    private final Set<Pair<Integer, Integer>> edges;

    private final Set<InvalidationListener> listeners;

    public Graph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();

        this.listeners = new HashSet<>();
    }

    public void addVertex(int key, double x, double y) {
        vertices.add(new Vertex(key, x, y));
        fireInvalidationEvent();
    }

    public void addEdge(int key1, int key2) {
        if (vertices.stream().map(Vertex::key).collect(Collectors.toSet()).containsAll(List.of(key1, key2))) {
            edges.add(new Pair<>(key1, key2));
        }
        fireInvalidationEvent();
    }

    private void fireInvalidationEvent() {
        for (InvalidationListener listener : listeners) {
            listener.invalidated(this);
        }
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    record Vertex(int key, double x, double y) {

    }
}
