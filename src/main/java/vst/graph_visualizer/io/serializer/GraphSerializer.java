package vst.graph_visualizer.io.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;

import java.io.IOException;

public class GraphSerializer extends StdSerializer<Graph> {

    public GraphSerializer() {
        super(Graph.class);
    }

    @Override
    public void serialize(Graph graph, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("vertices");
        for (Vertex v : graph.getVertices()) {
            jsonGenerator.writeObject(v);
        }
        jsonGenerator.writeEndArray();

        jsonGenerator.writeArrayFieldStart("edges");
        for (Edge e : graph.getEdges()) {
            jsonGenerator.writeObject(e);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
