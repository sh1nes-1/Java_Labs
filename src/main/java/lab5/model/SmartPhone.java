package lab5.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import lab4.service.NotOlderThanYears;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "can't be not null")
    private Integer id;

    @NotNull(message = "can't be not null")
    @NotEmpty(message = "can't be empty")
    private String name;

    @NotNull(message = "can't be not null")
    @Min(value = 1000, message = "can't be less than 1000")
    @Max(value = 500000, message = "can't be more than 500000")
    private Integer price;

    @NotNull(message = "can't be not null")
    @NotOlderThanYears(value = 5, message = "can't be older than 5 years")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate releaseDate;

    @NotNull(message = "can't be not null")
    private Color color;

    @NotNull(message = "can't be not null")
    @Min(value = 1024, message = "can't be less than 1024")
    @Max(value = 8096, message = "can't be more than 8096")
    private Integer ram;

    @NotNull(message = "can't be not null")
    @Min(value = 3, message = "can't be less than 3")
    @Max(value = 10, message = "can't be more than 10")
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

            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<SmartPhone>> constraintViolations = validator.validate(smartPhone);

            //Show errors
            if (constraintViolations.size() > 0) {
                Set<String> exceptionDetails = new HashSet<>();
                for (ConstraintViolation<SmartPhone> violation : constraintViolations) {
                    exceptionDetails.add(violation.getPropertyPath().toString() + " " + violation.getMessage());
                }
                throw new IllegalStateException(exceptionDetails.toString());
            }

            return smartPhone;
        }

    }

}
