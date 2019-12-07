package lab7;

import lab7.model.SmartPhone;
import lab7.dto.SmartPhoneDTO;
import lab7.mapper.SmartPhoneMapper;
import org.testng.annotations.Test;

import java.time.LocalDate;

public class TestMapper {

    @Test
    public void testSmartPhoneMapper() {
        SmartPhone smartPhone = new SmartPhone.Builder()
                .setName("Samsung Galaxy A30")
                .setDiagonal(6.4)
                .setColor(SmartPhone.Color.BLACK)
                .setRam(3072)
                .setReleaseDate(LocalDate.of(2019, 6, 15))
                .setPrice(5500)
                .build();

        SmartPhoneDTO smartPhoneDTO = SmartPhoneMapper.INSTANCE.smartPhoneToDTO(smartPhone);
        System.out.println(smartPhoneDTO);
    }
}
