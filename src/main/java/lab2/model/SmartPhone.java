package lab2.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

/**
 * Class, that represents characteristics of SmartPhone
 * Can be created using pattern Builder
 * Example: SmartPhone smartPhone = new SmartPhone.Builder()
 * .setName("Some Phone")
 * .setColor(Color.BLACK)
 * .setRam(2048)
 * .build();
 */
@JsonDeserialize(builder = SmartPhone.Builder.class)
public class SmartPhone implements Serializable {

    public enum Color {
        BLACK,
        WHITE,
        BLUE,
        RED,
        GOLD
    }


    // all fields cant be changed and assigned once in builder
    private Integer id;
    private String name;
    private Integer price;

    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate releaseDate;

    private Color color;
    private Integer ram;
    private Double diagonal;

    private SmartPhone() {
        // Private constructor to deny creating new instance outside by constructor
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    /**
     * @return price in UAH
     */
    public Integer getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Color getColor() {
        return color;
    }

    /**
     * @return RAM capacity in MegaBytes
     */
    public Integer getRam() {
        return ram;
    }

    /**
     * @return diagonal of display in inches
     */
    public Double getDiagonal() {
        return diagonal;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SmartPhone that = (SmartPhone) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        if (!price.equals(that.price)) return false;
        if (!releaseDate.equals(that.releaseDate)) return false;
        if (color != that.color) return false;
        if (!ram.equals(that.ram)) return false;
        return diagonal.equals(that.diagonal);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + color.hashCode();
        result = 31 * result + ram.hashCode();
        result = 31 * result + diagonal.hashCode();
        return result;
    }

    /**
     * Use this to build new SmartPhone
     */
    @JsonPOJOBuilder(withPrefix = "set")
    public static class Builder {

        SmartPhone smartPhone;

        public Builder() {
            smartPhone = new SmartPhone();
        }

        public Builder setId(Integer id) {
            smartPhone.id = id;
            return this;
        }

        public Builder setName(String name) {
            smartPhone.name = name;
            return this;
        }

        /**
         * @param price Price > 0
         * @return instance of this Builder
         */
        public Builder setPrice(Integer price) {
            smartPhone.price = price;
            return this;
        }

        @JsonDeserialize(using = LocalDateDeserializer.class)
        public Builder setReleaseDate(LocalDate releaseDate) {
            smartPhone.releaseDate = releaseDate;
            return this;
        }

        public Builder setColor(Color color) {
            smartPhone.color = color;
            return this;
        }

        /**
         * @param ram capacity in MegaBytes
         * @return instance of this Builder
         */
        public Builder setRam(Integer ram) {
            smartPhone.ram = ram;
            return this;
        }

        /**
         * @param diagonal Double
         * @return instance of this Builder
         */
        public Builder setDiagonal(Double diagonal) {
            smartPhone.diagonal = diagonal;
            return this;
        }

        /**
         * Use it after calling all setters
         *
         * @return instance of SmartPhone
         * @throws IllegalStateException if you do not set some of fields
         */
        public SmartPhone build() throws IllegalStateException {
            // Not initialized fields
            Set<String> notInitializedFields = new HashSet<>();

            if (smartPhone.id == null) notInitializedFields.add("id");
            if (smartPhone.name == null) notInitializedFields.add("name");
            if (smartPhone.price == null) notInitializedFields.add("price");
            if (smartPhone.releaseDate == null) notInitializedFields.add("releaseDate");
            if (smartPhone.color == null) notInitializedFields.add("color");
            if (smartPhone.ram == null) notInitializedFields.add("ram");
            if (smartPhone.diagonal == null) notInitializedFields.add("diagonal");

            if (notInitializedFields.size() > 0) {
                throw new IllegalStateException("Not initialized fields: " + notInitializedFields.toString());
            }

            // Value <= 0
            Set<String> lessZeroFields = new HashSet<>();

            if (smartPhone.id <= 0) lessZeroFields.add("id");
            if (smartPhone.price <= 0) lessZeroFields.add("price");
            if (smartPhone.ram <= 0) lessZeroFields.add("ram");
            if (smartPhone.diagonal <= 0) lessZeroFields.add("diagonal");

            if (lessZeroFields.size() > 0) {
                throw new IllegalStateException("Fields must be greater than zero: " + lessZeroFields.toString());
            }

            return smartPhone;
        }

    }

}
