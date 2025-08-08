package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.message.response.MagieVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MagieMapper {
    // Convertir l'entité persistante en DTO
    MagieVolatile toMagieDto(Magie magie);

    // Convertir un DTO en entité persistante
    Magie toMagieEntity(MagieVolatile dto);

}
