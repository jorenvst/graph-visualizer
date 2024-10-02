package vst.treevisualizer.treevisualizer.toolbar.menu;

public enum Menus {
    FILE(new FileButton());

    private final TopMenu item;

    Menus(TopMenu item) {
        this.item = item;
    }

    public TopMenu get() {
        return item;
    }
}
