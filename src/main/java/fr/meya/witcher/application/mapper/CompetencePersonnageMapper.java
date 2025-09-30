package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.CompetencePersonnage;
import fr.meya.witcher.message.response.CompetencePersonnageVolatile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompetencePersonnageMapper {
    CompetencePersonnageVolatile toDto(CompetencePersonnage entity);
    CompetencePersonnage toEntity(CompetencePersonnageVolatile dto);
    List<CompetencePersonnageVolatile> toDtoList(List<CompetencePersonnage> list);
    List<CompetencePersonnage> toEntityList(List<CompetencePersonnageVolatile> list);
}
