package vst.treevisualizer.gui.tree;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

public class TreeNode extends StackPane {

    private final int key;

    public TreeNode(int key) {
        this.key = key;

        Circle circle = new Circle(25);
        circle.setFill(Color.web("white"));
        this.getChildren().add(circle);

        Text text = new Text(Integer.toString(key));
        text.setBoundsType(TextBoundsType.VISUAL);
        this.getChildren().add(text);
    }

    public int getKey() {
        return key;
    }
}
