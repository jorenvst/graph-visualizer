package vst.treevisualizer.treevisualizer.dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import vst.treevisualizer.treevisualizer.TreeVisualizer;

public class KeyDialog extends Stage {

    private final TextField keyField;

    public KeyDialog() {
        HBox root = new HBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        root.getStylesheets().add(TreeVisualizer.class.getResource("/vst/style/tree-visualizer.css").toExternalForm());
        root.getStyleClass().add("key-dialog");

        setScene(new Scene(root));
        setTitle("Node Add");
        setResizable(false);
        setAlwaysOnTop(true);

        this.keyField = new TextField();
        root.getChildren().add(keyField);
        keyField.setPromptText("node key");
        keyField.requestFocus();
        keyField.getStyleClass().add("key-field");

        keyField.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                close();
            }
        });

        Button submitButton = new Button();
        submitButton.setText("OK");
        root.getChildren().add(submitButton);
        submitButton.getStyleClass().add("submit-button");

        submitButton.setOnAction(e -> close());
    }

    public Integer getKey() {
        try {
            return Integer.parseInt(keyField.getText());
        } catch (NumberFormatException e) {
            System.err.println("invalid key");
            return null;
        }
    }
}
