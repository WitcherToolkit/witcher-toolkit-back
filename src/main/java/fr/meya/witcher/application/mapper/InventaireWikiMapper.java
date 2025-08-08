package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.InventaireWiki;
import fr.meya.witcher.message.response.InventaireWikiVolatile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventaireWikiMapper {

    InventaireWikiVolatile toDto(InventaireWiki inventaireWiki);

    InventaireWiki toEntity(InventaireWikiVolatile dto);

    List<InventaireWikiVolatile> toDtoList(List<InventaireWiki> list);

    List<InventaireWiki> toEntityList(List<InventaireWikiVolatile> list);
}