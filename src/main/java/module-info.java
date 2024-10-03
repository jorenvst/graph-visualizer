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
    exports vst.treevisualizer.treevisualizer.visualizer to com.fasterxml.jackson.databind;
    opens vst.treevisualizer.treevisualizer.visualizer to com.fasterxml.jackson.databind;
}