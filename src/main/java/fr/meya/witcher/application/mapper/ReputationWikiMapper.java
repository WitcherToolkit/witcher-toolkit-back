package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.model.persistent.ReputationWiki;
import fr.meya.witcher.message.response.ReputationWikiVolatile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReputationWikiMapper {

    /**
     * Convertit une entité ReputationWiki en DTO ReputationWikiVolatile.
     *
     * @param entity l'entité persistante à convertir
     * @return le DTO correspondant
     */
    ReputationWikiVolatile toDto(ReputationWiki entity);

    /**
     * Convertit un DTO ReputationWikiVolatile en entité ReputationWiki.
     *
     * @param dto le DTO à convertir
     * @return l'entité correspondante
     */
    ReputationWiki toEntity(ReputationWikiVolatile dto);

    /**
     * Méthode appelée automatiquement après la conversion d'un DTO en entité
     * (utile pour fixer les relations inverses non mappées automatiquement).
     *
     * @param entity L'entité ReputationWiki créée par MapStruct
     * @param race   La Race à laquelle cette ReputationWiki appartient (à fournir explicitement)
     */
    @AfterMapping
    default void linkRace(@MappingTarget ReputationWiki entity, Race race) {
        if (entity != null) {
            entity.setRace(race);
        }
    }
}

