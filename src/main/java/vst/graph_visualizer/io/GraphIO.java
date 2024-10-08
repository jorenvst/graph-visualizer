package vst.graph_visualizer.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.stage.FileChooser;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphPane;
import vst.graph_visualizer.graph.Vertex;
import vst.graph_visualizer.io.deserializer.EdgeDeserializer;
import vst.graph_visualizer.io.deserializer.GraphDeserializer;
import vst.graph_visualizer.io.deserializer.VertexDeserializer;
import vst.graph_visualizer.io.serializer.EdgeSerializer;
import vst.graph_visualizer.io.serializer.GraphSerializer;
import vst.graph_visualizer.io.serializer.VertexSerializer;

import java.io.IOException;

public class GraphIO {

    public static void exportGraph(Graph graph) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addSerializer(Graph.class, new GraphSerializer());
            module.addSerializer(Vertex.class, new VertexSerializer());
            module.addSerializer(Edge.class, new EdgeSerializer());
            mapper.registerModule(module);

            mapper.writeValue(new FileChooser().showSaveDialog(graph.getScene().getWindow()), graph);
        } catch (IOException e) {
            throw new RuntimeException("could not export", e);
        }
    }

    public static Graph importGraph(GraphPane pane) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            SimpleModule module = new SimpleModule();
            module.addDeserializer(Graph.class, new GraphDeserializer());
            module.addDeserializer(Vertex.class, new VertexDeserializer());
            module.addDeserializer(Edge.class, new EdgeDeserializer());
            mapper.registerModule(module);

            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
            return mapper.readValue(chooser.showOpenDialog(pane.getScene().getWindow()), Graph.class);
        } catch (IOException e) {
            throw new RuntimeException("could not import", e);
        }
    }
}
