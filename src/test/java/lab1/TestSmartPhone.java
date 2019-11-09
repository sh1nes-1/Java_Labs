package lab1;

import lab1.model.SmartPhone;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestSmartPhone {

    @Test
    public void builderTest() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Test(expectedExceptions = IllegalStateException.class)
    public void builderNegativeTest1() {
        SmartPhone samsungA30 = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(-5500)
                .build();
    }

    @Test(expectedExceptions = IllegalStateException.class)
    public void builderNegativeTest2() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setColor(SmartPhone.Color.BLACK)
                .build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
