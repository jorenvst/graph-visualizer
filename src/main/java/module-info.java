module vst.treevisualizer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires jdk.compiler;


    opens vst.treevisualizer to javafx.fxml;
    exports vst.treevisualizer;
    exports vst.treevisualizer.old;
    opens vst.treevisualizer.old to javafx.fxml;
    exports vst.treevisualizer.treevisualizer;
    opens vst.treevisualizer.treevisualizer to javafx.fxml;
}