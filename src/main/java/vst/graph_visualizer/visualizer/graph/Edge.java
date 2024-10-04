package vst.graph_visualizer.visualizer.graph;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.scene.shape.Line;
import vst.graph_visualizer.toolbar.tools.Tools;
import vst.graph_visualizer.visualizer.Visualizer;

import java.io.IOException;

public class Edge extends Line {

    private final GraphNode node1;
    private final GraphNode node2;

    public Edge(GraphNode node1, GraphNode node2, Visualizer visualizer) {
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

    public GraphNode node1() {
        return node1;
    }

    public GraphNode node2() {
        return node2;
    }

    public static class EdgeSerializer extends StdSerializer<Edge> {

        public EdgeSerializer(Class<Edge> t) {
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

    public static class EdgeDeserializer extends StdDeserializer<Edge> {

        protected EdgeDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public Edge deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

            // return new Edge();
            return null;
        }
    }
}
