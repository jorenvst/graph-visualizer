package vst.graph_visualizer.graph;

import javafx.scene.shape.Line;

public class Edge extends Line implements GraphComponent {

    private final Vertex v1;
    private final Vertex v2;

    public Edge(Vertex v1, Vertex v2) {
        super();

        this.v1 = v1;
        this.v2 = v2;

        this.startXProperty().bind(v1.getCenter().xProperty());
        this.startYProperty().bind(v1.getCenter().yProperty());
        this.endXProperty().bind(v2.getCenter().xProperty());
        this.endYProperty().bind(v2.getCenter().yProperty());

        v1.addEdge(this);
        v2.addEdge(this);

        getStyleClass().add("edge");
    }

    public Vertex v1() {
        return v1;
    }

    public Vertex v2() {
        return v2;
    }
}
