package vst.graph_visualizer.graph;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class Vertex extends StackPane implements GraphComponent {

    private final int key;
    private final Coordinate center;
    private final ObservableSet<Edge> edges;

    private final Circle circle;
    private final Text text;

    private final BooleanProperty movable;

    // x and y are the coordinates of the circle center
    public Vertex(int key, double x, double y) {
        super();

        this.key = key;
        this.center = new Coordinate(x, y);
        this.edges = FXCollections.observableSet();
        this.movable = new SimpleBooleanProperty(false);

        this.circle = new Circle(25, Color.WHITE);
        this.getChildren().add(circle);

        this.layoutXProperty().bind(center.xProperty().subtract(circle.getRadius()));
        this.layoutYProperty().bind(center.yProperty().subtract(circle.getRadius()));

        this.text = new Text(Integer.toString(key));
        this.text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);

        // problem with dragging outside viewport and panning
        final Coordinate drag = new Coordinate(0, 0);
        setOnMousePressed(e -> {
            if (isMovable()) {
                drag.xProperty().set(getLayoutX() - e.getSceneX());
                drag.yProperty().set(getLayoutY() - e.getSceneY());
                setCursor(Cursor.MOVE);
            }
        });
        setOnMouseReleased(e -> setCursor(Cursor.DEFAULT));
        setOnMouseDragged(e -> {
            if (isMovable()) {
                getCenter().xProperty().set(e.getSceneX() + drag.x() + getRadius());
                getCenter().yProperty().set(e.getSceneY() + drag.y() + getRadius());
            }
        });
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

    public boolean isMovable() {
        return movable.get();
    }

    public BooleanProperty movableProperty() {
        return movable;
    }
}
