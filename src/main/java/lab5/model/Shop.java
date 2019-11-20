package lab5.model;

public class Shop {

    private Long id;
    private String name;
    private String imageUrl;

    //TODO: in properties define some path which be added to path

    public Shop(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
