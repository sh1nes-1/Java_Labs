package lab7.model;

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
    private Long id;

    @NotNull(message = "can't be null")
    @NotEmpty(message = "can't be empty")
    private String name;

    @NotNull(message = "can't be null")
    @Min(value = 1000, message = "can't be less than 1000")
    @Max(value = 500000, message = "can't be more than 500000")
    private Integer price;

    @NotNull(message = "can't be null")
    @NotOlderThanYears(value = 5, message = "can't be older than 5 years")
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDate releaseDate;

    @NotNull(message = "can't be null")
    private Color color;

    @NotNull(message = "can't be null")
    @Min(value = 1024, message = "can't be less than 1024")
    @Max(value = 8096, message = "can't be more than 8096")
    private Integer ram;

    @NotNull(message = "can't be null")
    @Min(value = 3, message = "can't be less than 3")
    @Max(value = 10, message = "can't be more than 10")
    private Double diagonal;

    public SmartPhone() {

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

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return price in UAH
     */
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return RAM capacity in MegaBytes
     */
    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    /**
     * @return diagonal of display in inches
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartPhone that = (SmartPhone) o;
        return name.equals(that.name) &&
                price.equals(that.price) &&
                releaseDate.equals(that.releaseDate) &&
                color == that.color &&
                ram.equals(that.ram) &&
                diagonal.equals(that.diagonal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, releaseDate, color, ram, diagonal);
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

        public Builder setId(Long id) {
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
