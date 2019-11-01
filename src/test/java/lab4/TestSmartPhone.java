package lab4;

import lab4.model.SmartPhone;
import org.testng.annotations.Test;

import javax.validation.Validation;
import javax.validation.Validator;

public class TestSmartPhone {

    @Test
    public void testBuilder() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setColor(SmartPhone.Color.GOLD)
                .setPrice(-5)
                .build();


    }

}
