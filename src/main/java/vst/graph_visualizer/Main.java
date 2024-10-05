package vst.graph_visualizer;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.tools.Tool;

import java.util.Arrays;
import java.util.List;

// TODO: Ctrl-Z feature
public class Main extends Application {

    private BorderPane root;

    private Graph graph;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        root.getStylesheets().add(getClass().getResource("/vst/style/tree-visualizer.css").toExternalForm());

        initGraph();
        initMenu();
        initToolBar();

        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    private void initGraph() {
        graph = new Graph();

        ScrollPane pane = new ScrollPane();
        pane.setContent(graph);
        root.setCenter(pane);
    }

    private void initMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem exportFile = new MenuItem("export");
        MenuItem importFile = new MenuItem("import");
        fileMenu.getItems().addAll(exportFile, importFile);
        MenuBar menuBar = new MenuBar(fileMenu);
        root.setTop(menuBar);
    }

    // TODO: move to css
    private void initToolBar() {
        List<ToggleButton> tools = Arrays.stream(Tool.values()).map(Tool::get).toList();

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(tools);
        toggleGroup.selectToggle(toggleGroup.getToggles().getFirst());
        // ensure that there's always a selected tool
        toggleGroup.selectedToggleProperty().addListener((obs, o, n) -> {
            if (n == null) {
                o.setSelected(true);
            }
        });

        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        toolBar.getItems().addAll(tools);

        VBox toolBarWrapper = new VBox();
        toolBarWrapper.setAlignment(Pos.CENTER);
        toolBarWrapper.getChildren().add(toolBar);
        toolBarWrapper.getStyleClass().add("tool-bar-wrapper");
        root.setLeft(toolBarWrapper);
    }

    public static void main(String[] args) {
        launch();
    }
}
