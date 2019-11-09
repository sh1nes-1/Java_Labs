package lab5.model;

import lab1.model.Catalog;

public class Shop {

    private String name;
    private Catalog catalog;

    {
        catalog = new Catalog();
    }

    public Shop(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Catalog getCatalog() {
        return catalog;
    }

}
