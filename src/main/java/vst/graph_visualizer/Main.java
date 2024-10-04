package vst.graph_visualizer;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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
        ImageView addVertexImage = new ImageView(getClass().getResource("/vst/sidebar/plus.png").toExternalForm());
        addVertexImage.setPreserveRatio(true);
        addVertexImage.setFitWidth(40);
        ToggleButton addVertexButton = new ToggleButton();
        addVertexButton.setTooltip(new Tooltip("add vertex"));
        addVertexButton.setGraphic(addVertexImage);

        ImageView addEdgeImage = new ImageView(getClass().getResource("/vst/sidebar/line.png").toExternalForm());
        addEdgeImage.setPreserveRatio(true);
        addEdgeImage.setFitWidth(40);
        ToggleButton addEdgeButton = new ToggleButton();
        addEdgeButton.setTooltip(new Tooltip("add edge"));
        addEdgeButton.setGraphic(addEdgeImage);

        ImageView moveVertexImage = new ImageView(getClass().getResource("/vst/sidebar/move.png").toExternalForm());
        moveVertexImage.setPreserveRatio(true);
        moveVertexImage.setFitWidth(40);
        ToggleButton moveVertexButton = new ToggleButton();
        moveVertexButton.setTooltip(new Tooltip("move vertex"));
        moveVertexButton.setGraphic(moveVertexImage);

        ImageView deleteImage = new ImageView(getClass().getResource("/vst/sidebar/trash.png").toExternalForm());
        deleteImage.setPreserveRatio(true);
        deleteImage.setFitWidth(40);
        ToggleButton deleteButton = new ToggleButton();
        deleteButton.setTooltip(new Tooltip("delete vertex/edge"));
        deleteButton.setGraphic(deleteImage);

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
