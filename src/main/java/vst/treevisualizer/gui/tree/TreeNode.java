package vst.treevisualizer.gui.tree;

import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class TreeNode extends StackPane {

    private final int key;

    private final BooleanProperty selected = new SimpleBooleanProperty(false);
    private final DoubleProperty centerX = new SimpleDoubleProperty();
    private final DoubleProperty centerY = new SimpleDoubleProperty();

    private final Circle circle;

    public TreeNode(int key) {
        this.key = key;

        circle = new Circle(25);
        circle.setFill(Color.web("white"));
        this.getChildren().add(circle);

        Text text = new Text(Integer.toString(key));
        text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);

        centerX.bind(layoutXProperty().add(widthProperty().divide(2)));
        centerY.bind(layoutYProperty().add(heightProperty().divide(2)));
    }

    public int getKey() {
        return key;
    }

    public BooleanProperty selectedProperty() {
        return selected;
    }

    public DoubleProperty centerX() {
        return centerX;
    }

    public DoubleProperty centerY() {
        return centerY;
    }

}
