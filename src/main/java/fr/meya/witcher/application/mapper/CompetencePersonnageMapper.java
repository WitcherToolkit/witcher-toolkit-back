package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.CompetencePersonnage;
import fr.meya.witcher.message.response.CompetencePersonnageVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetencePersonnageMapper {

    CompetencePersonnageVolatile toDto(CompetencePersonnage entity);

    @Mapping(target = "competence", ignore = true)
    @Mapping(target = "personnage", ignore = true)
    @Mapping(target = "id", ignore = true)
    CompetencePersonnage toEntity(CompetencePersonnageVolatile dto);

    List<CompetencePersonnageVolatile> toDtoList(List<CompetencePersonnage> list);

    List<CompetencePersonnage> toEntityList(List<CompetencePersonnageVolatile> list);
}
