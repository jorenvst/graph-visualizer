package vst.treevisualizer.treevisualizer.toolbar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import vst.treevisualizer.treevisualizer.visualizer.Visualizer;

import java.util.ArrayList;
import java.util.List;

public class ToolBar extends VBox {

    private Visualizer visualizer;

    private final List<Tool> tools;
    private final ObjectProperty<Tool> selectedTool;

    public ToolBar(Tool... tools1) {
        tools = new ArrayList<>();
        tools.addAll(List.of(tools1));
        getChildren().addAll(tools);

        for (Tool tool : tools) {
            tool.setToolBar(this);
        }
        selectedTool = new SimpleObjectProperty<>();
        select(tools.getFirst());

        setAlignment(Pos.CENTER);
        setSpacing(10);
        getStyleClass().add("toolbar");
    }

    public void select(Tool tool) {
        selectedTool.set(tool);
        tool.selectedProperty().set(true);
        for (Tool other : tools) {
            if (!other.equals(tool)) {
                other.selectedProperty().set(false);
                other.clearSelectedNodes();
            }
        }
    }

    public ObjectProperty<Tool> selectedToolProperty() {
        return selectedTool;
    }

    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }

    public Visualizer getVisualizer() {
        return visualizer;
    }

    public List<Tool> getTools() {
        return tools;
    }
}
