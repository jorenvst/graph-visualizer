package vst.graph_visualizer.io.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import vst.graph_visualizer.graph.Vertex;

import java.io.IOException;

public class VertexSerializer extends StdSerializer<Vertex> {

    public VertexSerializer() {
        super(Vertex.class);
    }

    @Override
    public void serialize(Vertex vertex, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("key", vertex.getKey());
        jsonGenerator.writeNumberField("x", vertex.getCenter().x());
        jsonGenerator.writeNumberField("y", vertex.getCenter().y());
        jsonGenerator.writeEndObject();
    }
}
