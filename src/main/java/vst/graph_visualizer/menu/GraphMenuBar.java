package vst.graph_visualizer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import static vst.graph_visualizer.Sizes.MENU_BAR_HEIGHT;

public class GraphMenuBar extends MenuBar {

    private final MenuItem exportFile;
    private final MenuItem importFile;

    public GraphMenuBar() {
        Menu fileMenu = new Menu("File");
        exportFile = new MenuItem("export");
        importFile = new MenuItem("import");
        fileMenu.getItems().addAll(exportFile, importFile);

        getMenus().add(fileMenu);
        setPrefHeight(MENU_BAR_HEIGHT);
    }

    public MenuItem getExportFile() {
        return exportFile;
    }

    public MenuItem getImportFile() {
        return importFile;
    }
}
