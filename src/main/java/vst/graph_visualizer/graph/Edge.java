package vst.graph_visualizer.graph;

import javafx.scene.shape.Line;

public class Edge extends Line {

    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        super();

        this.v1 = v1;
        this.v2 = v2;

        this.startXProperty().bind(v1.getCenter().x());
        this.startYProperty().bind(v1.getCenter().y());
        this.endXProperty().bind(v2.getCenter().x());
        this.endYProperty().bind(v2.getCenter().y());

        v1.addEdge(this);
        v2.addEdge(this);
    }

    public Vertex getStartVertex() {
        return v1;
    }

    public Vertex getEndVertex() {
        return v2;
    }
}
