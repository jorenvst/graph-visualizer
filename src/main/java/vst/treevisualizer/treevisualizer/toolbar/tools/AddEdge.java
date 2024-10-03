package vst.treevisualizer.treevisualizer.toolbar.tools;

import javafx.scene.control.Tooltip;
import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class AddEdge extends Tool {

    public AddEdge() {
        super("/vst/sidebar/line.png");
        setTooltip(new Tooltip("add edge"));
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node == null) {
            return;
        }

        // selection and deselection of nodes
        if (selectedNodes.contains(node)) {
            selectedNodes.remove(node);
        } else {
            selectedNodes.add(node);
        }

        if (selectedNodes.size() == 2) {
            sideBar.getVisualizer().addEdge(selectedNodes.getFirst(), selectedNodes.get(1));
            selectedNodes.clear();
        }
    }

}
