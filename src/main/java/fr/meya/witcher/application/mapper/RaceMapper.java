package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.message.response.RaceVolatile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(
        componentModel = "spring",
        uses = {ReputationWikiMapper.class, ParticulariteMapper.class}
)
public interface RaceMapper {

    @Mapping(target = "reputationWikiList", source = "reputationWikiList")
    @Mapping(target = "particulariteList", source = "particulariteList")
    RaceVolatile toRaceDto(Race race);

    @Mapping(target = "reputationWikiList", source = "reputationWikiList")
    @Mapping(target = "particulariteList", source = "particulariteList")
    Race toRaceEntity(RaceVolatile dto);

    // Méthode pour mise à jour partielle d'une entité existante
    void updateRaceFromVolatile(RaceVolatile dto, @MappingTarget Race entity);

    // Méthode post-mapping pour fixer les relations inverses
    @AfterMapping
    default void linkRelations(@MappingTarget Race race) {
        if (race.getReputationWikiList() != null) {
            race.getReputationWikiList().forEach(rw -> rw.setRace(race));
        }
        if (race.getParticulariteList() != null) {
            race.getParticulariteList().forEach(p -> p.setRace(race));
        }
    }
}

