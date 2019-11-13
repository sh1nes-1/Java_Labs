package lab5.model;

import lab1.model.Catalog;

public class Shop {

    private Integer id;
    private String name;
    private Catalog catalog;

    // path

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
