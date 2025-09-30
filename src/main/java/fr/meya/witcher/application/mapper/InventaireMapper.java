package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Inventaire;
import fr.meya.witcher.message.response.InventaireVolatile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventaireMapper {

    InventaireVolatile toDto(Inventaire inventaire);

    Inventaire toEntity(InventaireVolatile dto);

    List<InventaireVolatile> toDtoList(List<Inventaire> list);

    List<Inventaire> toEntityList(List<InventaireVolatile> list);
}
