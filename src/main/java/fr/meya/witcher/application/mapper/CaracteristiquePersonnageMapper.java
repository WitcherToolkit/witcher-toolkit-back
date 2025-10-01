package fr.meya.witcher.application.mapper;


import fr.meya.witcher.domain.model.persistent.CaracteristiquePersonnage;
import fr.meya.witcher.message.response.CaracteristiquePersonnageVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaracteristiquePersonnageMapper {

    CaracteristiquePersonnageVolatile toDto(CaracteristiquePersonnage entity);

    @Mapping(target = "caracteristique", ignore = true)
    @Mapping(target = "personnage", ignore = true)
    @Mapping(target = "id", ignore = true)
    CaracteristiquePersonnage toEntity(CaracteristiquePersonnageVolatile dto);

    List<CaracteristiquePersonnageVolatile> toDtoList(List<CaracteristiquePersonnage> list);

    List<CaracteristiquePersonnage> toEntityList(List<CaracteristiquePersonnageVolatile> list);
}
