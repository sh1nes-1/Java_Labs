package lab7.mapper;

import lab7.dto.CatalogDTO;
import lab7.model.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {

    CatalogMapper INSTANCE = Mappers.getMapper( CatalogMapper.class );

    CatalogDTO getDto(Catalog catalog);

    Catalog getCatalog(CatalogDTO catalogDTO);

    List<CatalogDTO> getDtoList(List<Catalog> catalogs);

}
