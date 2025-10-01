package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Personnage;
import fr.meya.witcher.message.response.PersonnageVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                UserMapper.class,
                RaceMapper.class,
                ProfessionMapper.class,
                InventaireMapper.class,
                CaracteristiquePersonnageMapper.class,
                CompetencePersonnageMapper.class
        }
)
public interface PersonnageMapper {

    // Convertir l'entité persistante en DTO
    PersonnageVolatile toPersonnageDto(Personnage magie);

    // Convertir un DTO en entité persistante
    @Mapping(target = "race", ignore = true)
    @Mapping(target = "profession", ignore = true)
    @Mapping(target = "rituelPersonnageList", ignore = true)
    @Mapping(target = "envoutementPersonnageList", ignore = true)
    Personnage toPersonnageEntity(PersonnageVolatile dto);

}
