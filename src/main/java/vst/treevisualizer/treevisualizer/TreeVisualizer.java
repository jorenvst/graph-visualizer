package vst.treevisualizer.treevisualizer;

import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import vst.treevisualizer.treevisualizer.toolbar.*;
import vst.treevisualizer.treevisualizer.visualizer.Visualizer;

public class TreeVisualizer extends BorderPane {

    private final Visualizer visualizer;
    private final ToolBar toolBar;

    private final Tool addNode = new AddNode();
    private final Tool addEdge = new AddEdge();
    private final Tool move = new Move();
    private final Tool delete = new Delete();

    public TreeVisualizer() {
        toolBar = new ToolBar(addNode, addEdge, move, delete);
        visualizer = new Visualizer(toolBar);
        toolBar.setVisualizer(visualizer);

        setLeft(toolBar);
        setCenter(visualizer);

        getStylesheets().add(getClass().getResource("/vst/style/tree-visualizer.css").toExternalForm());
    }

    public void initialize() {
        // TODO: make more obj oriented (enum?)
        getScene().setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.N) {
                toolBar.select(addNode);
            }
            if (e.getCode() == KeyCode.E) {
                toolBar.select(addEdge);
            }
            if (e.getCode() == KeyCode.M) {
                toolBar.select(move);
            }
            if (e.getCode() == KeyCode.D) {
                toolBar.select(delete);
            }
        });
    }
}
