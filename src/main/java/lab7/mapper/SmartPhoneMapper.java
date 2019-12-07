package lab7.mapper;

import lab7.dto.SmartPhoneDTO;
import lab7.model.SmartPhone;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SmartPhoneMapper {
    SmartPhoneMapper INSTANCE = Mappers.getMapper( SmartPhoneMapper.class );

    SmartPhoneDTO smartPhoneToDTO(SmartPhone smartPhone);

}
