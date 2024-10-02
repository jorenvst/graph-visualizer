package vst.treevisualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import vst.treevisualizer.treevisualizer.TreeVisualizer;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        TreeVisualizer root = new TreeVisualizer();
        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Tree Visualizer");
        stage.setScene(scene);
        stage.show();
        root.initialize();
    }

    public static void main(String[] args) {
        launch();
    }
}