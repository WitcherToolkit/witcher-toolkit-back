package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.PersonnageVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonnageMapper {

    // Convertir l'entité persistante en DTO
    PersonnageVolatile toPersonnageDto(Personnage magie);

    // Convertir un DTO en entité persistante
    Personnage toPersonnageEntity(PersonnageVolatile dto);

}
