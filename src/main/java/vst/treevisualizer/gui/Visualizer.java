package vst.treevisualizer.gui;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class Visualizer extends VBox {

    private IntegerProperty depthProperty = new SimpleIntegerProperty();

    public Visualizer() {
        setAlignment(Pos.CENTER);
        setSpacing(100);
        setStyle("-fx-background-color: #383838;");

        depthProperty.bind(Bindings.add(Bindings.size(getChildren()), -1));
    }

    public void addLayer() {
        Layer layer = new Layer(depthProperty.get() + 1);
        getChildren().add(layer);
    }

    public void addNode(int key) {
        for (Node child : getChildren()) {
            Layer layer = (Layer) child;
            if (!layer.isFull()) {
                layer.addNode(key);
                return;
            }
        }
        addLayer();
        ((Layer)getChildren().getLast()).addNode(key);
    }

    public boolean containsNode(int key) {
        for (Node child : getChildren()) {
            Layer layer = (Layer) child;
            if (layer.containsKey(key)) {
                return true;
            }
        }
        return false;
    }
}
