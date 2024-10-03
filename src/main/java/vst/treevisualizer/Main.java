package vst.treevisualizer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vst.treevisualizer.toolbar.SideBar;
import vst.treevisualizer.toolbar.TopBar;
import vst.treevisualizer.visualizer.Visualizer;

// TODO: Ctrl-Z feature
// TODO: panning on Visualizer
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Tree Visualizer");
        stage.setScene(scene);
        stage.show();

        SideBar sideBar = new SideBar();
        TopBar topBar = new TopBar();
        Visualizer visualizer = new Visualizer(sideBar, topBar);
        sideBar.setVisualizer(visualizer);
        topBar.setVisualizer(visualizer);

        root.setLeft(sideBar);
        root.setTop(topBar);
        root.setCenter(visualizer);

        root.getStylesheets().add(getClass().getResource("/vst/style/tree-visualizer.css").toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }
}