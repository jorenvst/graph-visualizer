package vst.treevisualizer.visualizer.graph;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Cursor;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import vst.treevisualizer.toolbar.tools.Tools;
import vst.treevisualizer.visualizer.Visualizer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GraphNode extends StackPane {

    private final int key;

    private final BooleanProperty movable = new SimpleBooleanProperty(false);
    private final DoubleProperty centerX = new SimpleDoubleProperty();
    private final DoubleProperty centerY = new SimpleDoubleProperty();

    private final Set<Edge> edges = new HashSet<>();

    public GraphNode(int key, Visualizer visualizer) {
        this.key = key;

        Circle circle = new Circle(25);
        circle.setFill(Color.web("white"));
        this.getChildren().add(circle);

        Text text = new Text(Integer.toString(key));
        text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);

        centerX.bind(layoutXProperty().add(widthProperty().divide(2)));
        centerY.bind(layoutYProperty().add(heightProperty().divide(2)));
        movable.bind(visualizer.getSideBar().selectedToolProperty().isEqualTo(Tools.MOVE.get()));

        setOnMouseClicked(e -> visualizer.getSideBar().selectedToolProperty().get().apply(this, e.getX(), e.getY()));
        final Delta delta = new Delta();
        setOnMousePressed(e -> {
            if (movable.get()) {
                setCursor(Cursor.MOVE);
                delta.x = getLayoutX() - e.getSceneX();
                delta.y = getLayoutY() - e.getSceneY();
            }
        });
        setOnMouseDragged(e -> {
            if (movable.get()) {
                setCursor(Cursor.MOVE);
                setLayoutX(e.getSceneX() + delta.x);
                setLayoutY(e.getSceneY() + delta.y);
            }
        });
        setOnMouseReleased(e -> setCursor(Cursor.DEFAULT));
    }

    private static class Delta {
        double x; double y;
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

    public DoubleProperty centerX() {
        return centerX;
    }

    public DoubleProperty centerY() {
        return centerY;
    }

    public static class GraphNodeSerializer extends StdSerializer<GraphNode> {

        public GraphNodeSerializer(Class<GraphNode> t) {
            super(t);
        }

        @Override
        public void serialize(GraphNode node, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("key", node.getKey());
            jsonGenerator.writeNumberField("x", node.centerX().get());
            jsonGenerator.writeNumberField("y", node.centerY().get());
            jsonGenerator.writeEndObject();
        }
    }

    public static class GraphNodeDeserializer extends StdDeserializer<GraphNode> {

        protected GraphNodeDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public GraphNode deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
            JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

            //return new GraphNode();
            return null;
        }
    }
}