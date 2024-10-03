package vst.treevisualizer.treevisualizer.visualizer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.scene.shape.Line;
import vst.treevisualizer.treevisualizer.toolbar.tools.Tools;

import java.io.IOException;

public class Edge extends Line {

    private final TreeNode node1;
    private final TreeNode node2;

    public Edge(TreeNode node1, TreeNode node2, Visualizer visualizer) {
        this.node1 = node1;
        this.node2 = node2;
        node1.addEdge(this);
        node2.addEdge(this);

        startXProperty().bind(node1.centerX());
        startYProperty().bind(node1.centerY());
        endXProperty().bind(node2.centerX());
        endYProperty().bind(node2.centerY());

        getStyleClass().add("edge");

        setOnMouseClicked(e -> {
        if (visualizer.getSideBar().selectedToolProperty().get().equals(Tools.DELETE.get())) {
                visualizer.deleteEdge(this);
            }
        });
    }

    public TreeNode node1() {
        return node1;
    }

    public TreeNode node2() {
        return node2;
    }

    public static class EdgeSerializer extends StdSerializer<Edge> {

        protected EdgeSerializer(Class<Edge> t) {
            super(t);
        }

        @Override
        public void serialize(Edge edge, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("node1", edge.node1.getKey());
            jsonGenerator.writeNumberField("node2", edge.node2.getKey());
            jsonGenerator.writeEndObject();
        }
    }
}
