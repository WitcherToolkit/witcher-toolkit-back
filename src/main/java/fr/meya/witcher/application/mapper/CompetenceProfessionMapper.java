package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.CompetenceProfession;
import fr.meya.witcher.message.response.CompetenceProfessionVolatile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = CompetenceMapper.class)
public interface CompetenceProfessionMapper {

    CompetenceProfessionVolatile toDto(CompetenceProfession entity);

    CompetenceProfession toEntity(CompetenceProfessionVolatile dto);

    List<CompetenceProfessionVolatile> toDtoList(List<CompetenceProfession> list);

    List<CompetenceProfession> toEntityList(List<CompetenceProfessionVolatile> list);
}

