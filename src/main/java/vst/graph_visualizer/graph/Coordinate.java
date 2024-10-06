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

    public double x() {
        return x.get();
    }

    public double y() {
        return y.get();
    }

    public DoubleProperty xProperty() {
        return x;
    }

    public DoubleProperty yProperty() {
        return y;
    }

    public void set(double x, double y) {
        this.x.set(x);
        this.y.set(y);
    }

    @Override
    public String toString() {
        return "(" + x.get() + ", " + y.get() + ")";
    }
}
