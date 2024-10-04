package vst.graph_visualizer.dialog;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import vst.graph_visualizer.Main;

public class KeyDialog extends Stage {

    private final TextField keyField;

    public KeyDialog(Window source) {
        HBox root = new HBox();
        root.setSpacing(20);
        root.setAlignment(Pos.CENTER);

        root.getStylesheets().add(Main.class.getResource("/vst/style/tree-visualizer.css").toExternalForm());
        root.getStyleClass().add("key-dialog");

        setScene(new Scene(root));
        setTitle("Node Add");
        setResizable(false);

        this.keyField = new TextField();
        keyField.setPromptText("node key");
        keyField.requestFocus();
        keyField.getStyleClass().add("key-field");
        keyField.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                close();
            }
        });
        root.getChildren().add(keyField);

        Button submitButton = new Button();
        submitButton.setText("OK");
        submitButton.getStyleClass().add("submit-button");
        submitButton.setOnAction(e -> close());
        root.getChildren().add(submitButton);

        initModality(Modality.WINDOW_MODAL);
        initOwner(source);
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
