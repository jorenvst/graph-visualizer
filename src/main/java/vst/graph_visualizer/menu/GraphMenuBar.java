package vst.graph_visualizer.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import static vst.graph_visualizer.Sizes.MENU_BAR_HEIGHT;

public class GraphMenuBar extends MenuBar {

    public GraphMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem exportFile = new MenuItem("export");
        MenuItem importFile = new MenuItem("import");
        fileMenu.getItems().addAll(exportFile, importFile);

        getMenus().add(fileMenu);
        setPrefHeight(MENU_BAR_HEIGHT);
    }

}
