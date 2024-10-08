package vst.graph_visualizer.io.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import vst.graph_visualizer.graph.Vertex;

import java.io.IOException;

public class VertexDeserializer extends StdDeserializer<Vertex> {

    public VertexDeserializer() {
        super(Vertex.class);
    }

    @Override
    public Vertex deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return new Vertex(0, 0, 0);
    }
}
