package lab1.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Objects;
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
public class SmartPhone implements Serializable {

    public enum Color {
        BLACK,
        WHITE,
        BLUE,
        RED,
        GOLD
    }


    // all fields cant be changed and assigned once in builder
    private String name;
    private Integer price;
    private LocalDate releaseDate;

    private Color color;
    private Integer ram;
    private Double diagonal;

    private SmartPhone() {
        // Private constructor to deny creating new instance outside by constructor
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

        if (!Objects.equals(ram, that.ram)) return false;
        if (Double.compare(that.diagonal, diagonal) != 0) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(releaseDate, that.releaseDate)) return false;
        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name != null ? name.hashCode() : 0;
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + ram;
        temp = Double.doubleToLongBits(diagonal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }


    /**
     * Use this to build new SmartPhone
     */
    public static class Builder {

        SmartPhone smartPhone;

        public Builder() {
            smartPhone = new SmartPhone();
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
