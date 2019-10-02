package lab1.model;

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
