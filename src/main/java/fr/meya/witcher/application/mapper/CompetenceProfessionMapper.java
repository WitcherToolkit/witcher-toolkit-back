package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.CompetenceProfession;
import fr.meya.witcher.message.response.CompetenceProfessionVolatile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = CompetenceMapper.class)
public interface CompetenceProfessionMapper {

    @Mapping(target = "idCompetence", source = "id.idCompetence")
    @Mapping(target = "idProfession", source = "id.idProfession")
    CompetenceProfessionVolatile toDto(CompetenceProfession entity);

    @Mapping(target = "id.idProfession", source = "idProfession")
    @Mapping(target = "id.idCompetence", source = "idCompetence")
    CompetenceProfession toEntity(CompetenceProfessionVolatile dto);

    @Mapping(target = "idCompetence", source = "id.idCompetence")
    @Mapping(target = "idProfession", source = "id.idProfession")
    List<CompetenceProfessionVolatile> toDtoList(List<CompetenceProfession> list);

    List<CompetenceProfession> toEntityList(List<CompetenceProfessionVolatile> list);
}

