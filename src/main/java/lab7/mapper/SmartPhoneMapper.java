package lab7.mapper;

import lab7.dto.SmartPhoneDTO;
import lab7.model.SmartPhone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SmartPhoneMapper {
    SmartPhoneMapper INSTANCE = Mappers.getMapper( SmartPhoneMapper.class );

    SmartPhoneDTO getDto(SmartPhone smartPhone);

    default SmartPhone getSmartPhone(SmartPhoneDTO smartPhoneDTO) {
        return new SmartPhone.Builder()
                .setId(smartPhoneDTO.getId())
                .setName(smartPhoneDTO.getName())
                .setRam(smartPhoneDTO.getRam())
                .setPrice(smartPhoneDTO.getPrice())
                .setReleaseDate(smartPhoneDTO.getReleaseDate())
                .setColor(SmartPhone.Color.valueOf(smartPhoneDTO.getColor()))
                .setDiagonal(smartPhoneDTO.getDiagonal())
                .build();
    }

    List<SmartPhoneDTO> getDtoList(List<SmartPhone> list);

}
