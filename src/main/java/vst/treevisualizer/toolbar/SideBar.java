package vst.treevisualizer.toolbar;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import vst.treevisualizer.toolbar.tools.Tool;
import vst.treevisualizer.toolbar.tools.Tools;
import vst.treevisualizer.visualizer.Visualizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SideBar extends VBox {

    private Visualizer visualizer;

    private final List<Tools> tools;
    private final ObjectProperty<Tool> selectedTool;

    public SideBar() {
        tools = new ArrayList<>(Arrays.stream(Tools.values()).toList());
        getChildren().addAll(tools.stream().map(Tools::get).toList());

        for (Tools tool : tools) {
            tool.get().setSideBar(this);
        }
        selectedTool = new SimpleObjectProperty<>();
        select(tools.getFirst().get());

        setOnKeyReleased(e -> {
            for (Tools tool : tools) {
                if (e.getCode() == tool.shortcut()) {
                    select(tool.get());
                }
            }
        });

        setAlignment(Pos.CENTER);
        setSpacing(10);
        getStyleClass().add("side-bar");
    }

    public void select(Tool tool) {
        selectedTool.set(tool);
        tool.selectedProperty().set(true);
        for (Tools other : tools) {
            if (!other.get().equals(tool)) {
                other.get().selectedProperty().set(false);
                other.get().clearSelectedNodes();
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
}
