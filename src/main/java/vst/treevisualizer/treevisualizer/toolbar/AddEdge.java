package vst.treevisualizer.treevisualizer.toolbar;

import vst.treevisualizer.treevisualizer.visualizer.TreeNode;

public class AddEdge extends Tool {

    public AddEdge() {
        super("/vst/toolbar/line.png");
    }

    public void apply(TreeNode node, double mouseX, double mouseY) {
        if (node == null) {
            return;
        }
        selectedNodes.add(node);
        if (selectedNodes.size() == 2) {
            toolBar.getVisualizer().addEdge(selectedNodes.getFirst(), selectedNodes.get(1));
            selectedNodes.clear();
        }
    }

}
