package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = CaracteristiqueMapper.class
)
public interface CompetenceMapper {

    CompetenceVolatile toCompetenceDto(Competence competence);

    Competence toCompetenceEntity(CompetenceVolatile dto);
}
