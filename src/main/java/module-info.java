module vst.treevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires jdk.compiler;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires java.desktop;

    opens vst.graph_visualizer to javafx.fxml;
    exports vst.graph_visualizer;
    exports vst.graph_visualizer.tools;
    opens vst.graph_visualizer.tools to javafx.fxml;
    exports vst.graph_visualizer.io.deserializer to com.fasterxml.jackson.databind;
}