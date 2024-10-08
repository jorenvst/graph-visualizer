package vst.graph_visualizer.io.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import vst.graph_visualizer.graph.Edge;

import java.io.IOException;

public class EdgeSerializer extends StdSerializer<Edge> {

    public EdgeSerializer() {
        super(Edge.class);
    }

    @Override
    public void serialize(Edge edge, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("v1", edge.v1().getKey());
        jsonGenerator.writeNumberField("v2", edge.v2().getKey());
        jsonGenerator.writeEndObject();
    }
}
