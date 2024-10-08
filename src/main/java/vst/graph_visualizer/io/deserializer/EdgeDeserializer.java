package vst.graph_visualizer.io.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import vst.graph_visualizer.graph.Edge;

import java.io.IOException;

public class EdgeDeserializer extends StdDeserializer<Edge> {

    public EdgeDeserializer() {
        super(Edge.class);
    }

    @Override
    public Edge deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return null;
    }
}
