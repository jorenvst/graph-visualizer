package vst.graph_visualizer;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
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
        Graph graph = new Graph();

        // test
        Vertex v1 = new Vertex(15, 25, 25);
        Vertex v2 = new Vertex(8, 100, 100);
        Edge edge = new Edge(v1, v2);
        graph.addVertex(v1);
        graph.addVertex(v2);
        graph.addEdge(edge);

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
