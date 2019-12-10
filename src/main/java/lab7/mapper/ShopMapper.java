package lab7.mapper;

import lab7.dto.ShopDTO;
import lab7.model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ShopMapper {

    ShopMapper INSTANCE = Mappers.getMapper( ShopMapper.class );

    ShopDTO getDto(Shop shop);

    Shop getShop(ShopDTO shopDTO);

    List<ShopDTO> getDtoList(List<Shop> shops);
}
