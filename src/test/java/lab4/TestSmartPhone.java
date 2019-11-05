package lab4;

import lab4.model.SmartPhone;
import org.testng.annotations.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;

public class TestSmartPhone {

    @Test(expectedExceptions = IllegalStateException.class)
    public void testBuilder() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setColor(SmartPhone.Color.GOLD)
                .setReleaseDate(LocalDate.of(2019, 5, 5))
                .setPrice(-5)
                .build();
    }

    @Test
    public void testCustomValidation() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setId(5)
                .setColor(SmartPhone.Color.GOLD)
                .setReleaseDate(LocalDate.of(2019, 5, 5))
                .setPrice(5000)
                .setDiagonal(5.5)
                .setRam(2048)
                .setName("Xiaomi")
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void testCustomValidationNegative() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setId(5)
                .setColor(SmartPhone.Color.GOLD)
                .setReleaseDate(LocalDate.of(2013, 5, 5))
                .setPrice(5000)
                .setDiagonal(5.5)
                .setRam(2048)
                .setName("Xiaomi")
                .build();
    }

}
