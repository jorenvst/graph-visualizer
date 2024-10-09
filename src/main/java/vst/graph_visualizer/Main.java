package vst.graph_visualizer;

import javafx.application.Application;
import javafx.collections.SetChangeListener;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vst.graph_visualizer.graph.*;
import vst.graph_visualizer.io.GraphIO;
import vst.graph_visualizer.menu.GraphMenuBar;
import vst.graph_visualizer.tools.*;

import java.util.List;

import static vst.graph_visualizer.Sizes.MENU_BAR_HEIGHT;
import static vst.graph_visualizer.Sizes.TOOL_BAR_WIDTH;

// TODO: Ctrl-Z feature
public class Main extends Application {

    private BorderPane root;

    private GraphPane pane;
    private ToggleGroup toggleGroup;

    private GraphMenuBar menuBar;

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
        Graph graph = new Graph();

        graph.getVertices().addListener((SetChangeListener<Vertex>) change -> {
            if (change.wasAdded()) {
                Vertex vertex = change.getElementAdded();
                vertex.addEventFilter(MouseEvent.ANY, e -> ((Tool)toggleGroup.getSelectedToggle()).apply(e.getEventType(), pane.getGraph(), vertex, new Coordinate(e.getSceneX() - TOOL_BAR_WIDTH, e.getSceneY() - MENU_BAR_HEIGHT)));
            }
        });
        graph.getEdges().addListener((SetChangeListener<Edge>) change -> {
            if (change.wasAdded()) {
                Edge edge = change.getElementAdded();
                edge.addEventFilter(MouseEvent.ANY, e -> ((Tool)toggleGroup.getSelectedToggle()).apply(e.getEventType(), pane.getGraph(), edge, new Coordinate(e.getSceneX() - TOOL_BAR_WIDTH, e.getSceneY() - MENU_BAR_HEIGHT)));
            }
        });

        pane = new GraphPane(graph);
        root.setCenter(pane);

        pane.addEventFilter(MouseEvent.ANY, e -> ((Tool)toggleGroup.getSelectedToggle()).apply(e.getEventType(), pane.getGraph(), pane, new Coordinate(e.getSceneX() - TOOL_BAR_WIDTH, e.getSceneY() - MENU_BAR_HEIGHT)));
    }

    private void initMenu() {
        menuBar = new GraphMenuBar();
        menuBar.getExportFile().setOnAction(e -> GraphIO.exportGraph(pane.getGraph()));
        menuBar.getImportFile().setOnAction(e -> pane.setGraph(GraphIO.importGraph(pane)));
        root.setTop(menuBar);
    }

    private void initToolBar() {
        // TODO: extract class
        List<Tool> tools = List.of(new AddVertexTool(), new AddEdgeTool(), new MoveTool(), new DeleteTool());

        toggleGroup = new ToggleGroup();
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
        toolBar.setPrefWidth(TOOL_BAR_WIDTH);

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
