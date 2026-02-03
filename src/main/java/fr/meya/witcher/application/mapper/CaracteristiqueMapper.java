package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Caracteristique;
import fr.meya.witcher.message.response.CaracteristiqueVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CaracteristiqueMapper {

    // Convertir l'entité persistante en DTO
    CaracteristiqueVolatile toCaracteristiqueDto(Caracteristique caracteristique);

    // Convertir un DTO en entité persistante
    Caracteristique toCaracteristiqueEntity(CaracteristiqueVolatile caracteristiqueVolatile);

}
