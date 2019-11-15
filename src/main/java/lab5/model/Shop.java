package lab5.model;

import lab1.model.Catalog;

public class Shop {

    private Long id;
    private String name;
    private Catalog catalog;

    //TODO: in properties define some path which be added to path
    //TODO: path to image

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
