module vst.treevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires jdk.compiler;


    opens vst.treevisualizer to javafx.fxml;
    exports vst.treevisualizer;
}