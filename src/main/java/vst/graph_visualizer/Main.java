package vst.graph_visualizer;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import vst.graph_visualizer.graph.Edge;
import vst.graph_visualizer.graph.Graph;
import vst.graph_visualizer.graph.Vertex;
import vst.graph_visualizer.toolbar.SideBar;
import vst.graph_visualizer.toolbar.TopBar;
import vst.graph_visualizer.visualizer.Visualizer;

import java.util.Collection;

// TODO: Ctrl-Z feature
public class Main extends Application {

    private BorderPane root;

    @Override
    public void start(Stage stage) {
        root = new BorderPane();

        Scene scene = new Scene(root,800, 600);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();

        initGraph();
        initMenu();
        initToolBar();

        root.getStylesheets().add(getClass().getResource("/vst/style/tree-visualizer.css").toExternalForm());
    }

    private void initGraph() {
        Graph graph = new Graph();

        ScrollPane pane = new ScrollPane();
        pane.setContent(graph);
        pane.setPannable(true);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
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

    private void initToolBar() {
        ToggleButton addVertexButton = new ToggleButton("vertex");
        addVertexButton.setTooltip(new Tooltip("add vertex"));
        ToggleButton addEdgeButton = new ToggleButton("edge");
        addEdgeButton.setTooltip(new Tooltip("add edge"));
        ToggleButton moveVertexButton = new ToggleButton("move");
        moveVertexButton.setTooltip(new Tooltip("move vertex"));
        ToggleButton deleteButton = new ToggleButton("delete");
        deleteButton.setTooltip(new Tooltip("delete vertex/edge"));

        ToggleGroup toggleGroup = new ToggleGroup();
        toggleGroup.getToggles().addAll(addVertexButton, addEdgeButton, moveVertexButton, deleteButton);

        ToolBar toolBar = new ToolBar();
        toolBar.setOrientation(Orientation.VERTICAL);
        toolBar.getItems().addAll(addVertexButton, addEdgeButton, moveVertexButton, deleteButton);
        root.setLeft(toolBar);
    }

    public static void main(String[] args) {
        launch();
    }
}