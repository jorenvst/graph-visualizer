package vst.graph_visualizer.io.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

import java.io.IOException;
import java.util.Arrays;

public class GraphDeserializer extends StdDeserializer<Graph> {

    public GraphDeserializer() {
        super(Graph.class);
    }

    @Override
    public Graph deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);

        Graph graph = new Graph();
//        for (Vertex v : vertices) {
//            graph.addVertex(v);
//        }
        return graph;
    }
}
