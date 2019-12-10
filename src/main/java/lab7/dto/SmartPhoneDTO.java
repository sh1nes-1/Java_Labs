package lab7.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SmartPhoneDTO {

    private Long id;
    private String name;
    private Integer price;
    private LocalDate releaseDate;
    private String color;
    private Integer ram;
    private Double diagonal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Double diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public String toString() {
        return name +
                " " +
                ram / 1000 +
                " Gb " +
                color +
                " " +
                price +
                " UAH " +
                releaseDate.format(DateTimeFormatter.ofPattern("dd.MM.YYYY"));
    }
}
