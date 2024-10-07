package vst.graph_visualizer.graph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Vertex extends StackPane {

    private final int key;
    private final Coordinate center;
    private final ObservableSet<Edge> edges;

    private final Circle circle;
    private final Text text;

    // x and y are the coordinates of the circle center
    public Vertex(int key, double x, double y) {
        super();

        this.key = key;
        this.center = new Coordinate(x, y);
        this.edges = FXCollections.observableSet();

        this.circle = new Circle(25, Color.WHITE);
        this.getChildren().add(circle);

        this.layoutXProperty().bind(center.xProperty().subtract(circle.getRadius()));
        this.layoutYProperty().bind(center.yProperty().subtract(circle.getRadius()));

        this.text = new Text(Integer.toString(key));
        this.text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);
    }

    public int getKey() {
        return key;
    }

    public Coordinate getCenter() {
        return center;
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    public ObservableSet<Edge> getEdges() {
        return edges;
    }

    public double getRadius() {
        return circle.getRadius();
    }
}
