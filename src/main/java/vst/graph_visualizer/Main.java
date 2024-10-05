package vst.graph_visualizer;

import javafx.application.Application;
import javafx.collections.SetChangeListener;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vst.graph_visualizer.graph.Coordinate;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;
import vst.graph_visualizer.tools.*;

import java.util.List;

// TODO: Ctrl-Z feature
public class Main extends Application {

    private BorderPane root;

    private Graph graph;
    private ToggleGroup toggleGroup;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();
        root.getStylesheets().add(getClass().getResource("/vst/style/tree-visualizer.css").toExternalForm());

        initMenu();
        initToolBar();
        initGraph();

        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();
    }

    private void initGraph() {
        graph = new Graph();

        graph.getVertices().addListener((SetChangeListener<Vertex>) change -> {
            if (change.wasAdded()) {
                Vertex v = change.getElementAdded();
                v.setOnMouseClicked(e -> ((Tool)toggleGroup.getSelectedToggle()).apply(graph, v, new Coordinate(e.getX(), e.getY())));
            }
        });
        graph.getEdges().addListener((SetChangeListener<Edge>) change -> {
            if (change.wasAdded()) {
                Edge edge = change.getElementAdded();
                edge.setOnMouseClicked(e -> ((Tool)toggleGroup.getSelectedToggle()).apply(graph, edge, new Coordinate(e.getX(), e.getY())));
            }
        });

        ScrollPane pane = new ScrollPane();
        pane.pannableProperty().bind(graph.movableProperty().not());
        pane.setContent(graph);
        root.setCenter(pane);

        pane.setOnMouseClicked(e -> ((Tool)toggleGroup.getSelectedToggle()).apply(graph, null, new Coordinate(e.getX(), e.getY())));
    }

    private void initMenu() {
        Menu fileMenu = new Menu("File");
        MenuItem exportFile = new MenuItem("export");
        MenuItem importFile = new MenuItem("import");
        fileMenu.getItems().addAll(exportFile, importFile);
        MenuBar menuBar = new MenuBar(fileMenu);
        root.setTop(menuBar);
    }

    private void initToolBar() {
        List<Tool> tools = List.of(new AddVertexTool(), new AddEdgeTool(), new MoveTool(), new DeleteTool());

        toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(tools);
        toggleGroup.selectToggle(toggleGroup.getToggles().getFirst());
        // ensure that there's always a selected tool
        toggleGroup.selectedToggleProperty().addListener((obs, o, n) -> {
            if (n == null) {
                o.setSelected(true);
            }
            graph.setMovable(n instanceof MoveTool t);
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
