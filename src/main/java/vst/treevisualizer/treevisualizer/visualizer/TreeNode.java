package vst.treevisualizer.treevisualizer.visualizer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import vst.treevisualizer.treevisualizer.toolbar.Move;

import java.util.HashSet;
import java.util.Set;

public class TreeNode extends StackPane implements TreeObject {

    private final int key;
    private final Circle circle;

    private final BooleanProperty movable = new SimpleBooleanProperty(false);
    private final DoubleProperty centerX = new SimpleDoubleProperty();
    private final DoubleProperty centerY = new SimpleDoubleProperty();

    private final Set<Edge> edges = new HashSet<>();

    public TreeNode(int key, Visualizer visualizer) {
        this.key = key;

        circle = new Circle(25);
        circle.setFill(Color.web("white"));
        this.getChildren().add(circle);

        Text text = new Text(Integer.toString(key));
        text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);

        setOnMouseClicked(e -> visualizer.getToolBar().selectedToolProperty().get().apply(this, e.getX(), e.getY()));

        centerX.bind(layoutXProperty().add(widthProperty().divide(2)));
        centerY.bind(layoutYProperty().add(heightProperty().divide(2)));
        // TODO: clean up
        movable.bind(visualizer.getToolBar().selectedToolProperty().isEqualTo(visualizer.getToolBar().getTools().stream().filter(t -> t instanceof Move).toList().getFirst()));
    }

    public void addEdge(Edge edge) {
        edges.add(edge);
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public int getKey() {
        return key;
    }

    public void setPos(double x, double y) {
        setLayoutX(x - 25);
        setLayoutY(y - 25);
    }

    public boolean isMovable() {
        return movable.get();
    }

    public DoubleProperty centerX() {
        return centerX;
    }

    public DoubleProperty centerY() {
        return centerY;
    }
}