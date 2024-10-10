package vst.graph_visualizer.io.deserializer;

import java.util.List;

public record DeserializedGraph(List<DeserializedVertex> vertices, List<DeserializedEdge> edges) {

    @Override
    public String toString() {
        return vertices.toString() + edges.toString();
    }

}
