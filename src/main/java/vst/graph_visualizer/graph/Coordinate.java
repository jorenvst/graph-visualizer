package vst.graph_visualizer.graph;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Coordinate {

    private final DoubleProperty x;
    private final DoubleProperty y;

    public Coordinate(double x, double y) {
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
    }

    public DoubleProperty x() {
        return x;
    }

    public DoubleProperty y() {
        return y;
    }
}
