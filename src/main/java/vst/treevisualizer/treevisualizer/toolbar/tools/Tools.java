package vst.treevisualizer.treevisualizer.toolbar.tools;

import javafx.scene.input.KeyCode;

public enum Tools {

    ADD_NODE(new AddNode(), KeyCode.N),
    ADD_EDGE(new AddEdge(), KeyCode.E),
    MOVE(new Move(), KeyCode.M),
    DELETE(new Delete(), KeyCode.D);

    private final Tool tool;
    private final KeyCode shortcut;

    Tools(Tool tool, KeyCode shortCut) {
        this.tool = tool;
        this.shortcut = shortCut;
    }

    public Tool get() {
        return this.tool;
    }

    public KeyCode shortcut() {
        return shortcut;
    }

}
