package fr.meya.witcher.application.mapper;


import fr.meya.witcher.domain.model.persistent.CaracteristiquePersonnage;
import fr.meya.witcher.message.response.CaracteristiquePersonnageVolatile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CaracteristiquePersonnageMapper {

    CaracteristiquePersonnageVolatile toDto(CaracteristiquePersonnage entity);

    CaracteristiquePersonnage toEntity(CaracteristiquePersonnageVolatile dto);

    List<CaracteristiquePersonnageVolatile> toDtoList(List<CaracteristiquePersonnage> list);

    List<CaracteristiquePersonnage> toEntityList(List<CaracteristiquePersonnageVolatile> list);
}
