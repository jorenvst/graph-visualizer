package vst.graph_visualizer.io;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import javafx.stage.FileChooser;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.GraphPane;
import vst.graph_visualizer.graph.Vertex;
import vst.graph_visualizer.io.deserializer.DeserializedEdge;
import vst.graph_visualizer.io.deserializer.DeserializedGraph;
import vst.graph_visualizer.io.deserializer.DeserializedVertex;
import vst.graph_visualizer.io.serializer.EdgeSerializer;
import vst.graph_visualizer.io.serializer.GraphSerializer;
import vst.graph_visualizer.io.serializer.VertexSerializer;

import java.io.File;
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

            FileChooser chooser = new FileChooser();
            File file = chooser.showSaveDialog(graph.getScene().getWindow());
            if (file != null) {
            mapper.writeValue(file, graph);
            }
        } catch (IOException e) {
            throw new RuntimeException("could not export", e);
        }
    }

    public static Graph importGraph(GraphPane pane) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            FileChooser chooser = new FileChooser();
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json"));
            DeserializedGraph deserializedGraph = mapper.readValue(chooser.showOpenDialog(pane.getScene().getWindow()), DeserializedGraph.class);

            Graph graph = new Graph();

            for(DeserializedVertex v : deserializedGraph.vertices()) {
                graph.addVertex(new Vertex(v.key(), v.x(), v.y()));
            }

            for(DeserializedEdge e : deserializedGraph.edges()) {
                graph.addEdge(new Edge(
                        graph.getVertices().stream().filter(v -> v.getKey() == e.key1()).toList().getFirst(),
                        graph.getVertices().stream().filter(v -> v.getKey() == e.key2()).toList().getFirst())
                );
            }

            return graph;
        } catch (IOException e) {
            throw new RuntimeException("could not import", e);
        }
    }
}
