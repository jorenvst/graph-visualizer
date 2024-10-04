module vst.treevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires jdk.compiler;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens vst.graph_visualizer to javafx.fxml;
    exports vst.graph_visualizer;
    exports vst.graph_visualizer.visualizer to com.fasterxml.jackson.databind;
    opens vst.graph_visualizer.visualizer to com.fasterxml.jackson.databind;
    exports vst.graph_visualizer.visualizer.graph to com.fasterxml.jackson.databind;
    opens vst.graph_visualizer.visualizer.graph to com.fasterxml.jackson.databind;
}