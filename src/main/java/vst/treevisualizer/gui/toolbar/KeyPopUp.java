package vst.treevisualizer.gui.toolbar;

import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import vst.treevisualizer.gui.Visualizer;

import java.util.regex.Pattern;

public class KeyPopUp extends Stage {

    private final Visualizer visualizer;

    private final TextField keyField;

    public KeyPopUp(Visualizer visualizer) {
        this.visualizer = visualizer;

        HBox root = new HBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(15, 15, 15, 15));
        root.setStyle("-fx-background-color: #383838");

        setScene(new Scene(root));
        setTitle("Node Add");
        setResizable(false);
        setAlwaysOnTop(true);

        this.keyField = new TextField();
        root.getChildren().add(keyField);
        keyField.setPromptText("node key");
        keyField.getParent().requestFocus();

        keyField.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                submit();
            }
        });

        keyField.setStyle("-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;");

        Button submitButton = new Button();
        submitButton.setText("OK");
        root.getChildren().add(submitButton);

        submitButton.setOnAction(e -> submit());

        submitButton.setStyle("-fx-background-color: #0c75de; " +
                "-fx-text-fill: white;");
    }

    private void submit() {
        String key = keyField.getText();
        if (Pattern.matches("^[0-9]+$", key) && !visualizer.containsNode(Integer.parseInt(key))) {
            visualizer.addNode(Integer.parseInt(key));
            close();
        } else {
            System.out.println("invalid key");
        }
    }
}
