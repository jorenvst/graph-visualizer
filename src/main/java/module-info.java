module vst.treevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires jdk.compiler;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;

    opens vst.treevisualizer to javafx.fxml;
    exports vst.treevisualizer;
    exports vst.treevisualizer.visualizer to com.fasterxml.jackson.databind;
    opens vst.treevisualizer.visualizer to com.fasterxml.jackson.databind;
    exports vst.treevisualizer.visualizer.graph to com.fasterxml.jackson.databind;
    opens vst.treevisualizer.visualizer.graph to com.fasterxml.jackson.databind;
}