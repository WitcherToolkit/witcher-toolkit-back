package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Particularite;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.model.persistent.ReputationWiki;
import fr.meya.witcher.message.response.ParticulariteVolatile;
import fr.meya.witcher.message.response.RaceVolatile;
import fr.meya.witcher.message.response.ReputationWikiVolatile;
import org.springframework.stereotype.Component;

@Component
public class RaceMapper {

    //Convertir l'entité persistante en DTO
    public RaceVolatile toRaceDto(Race race) {
        return new RaceVolatile(
                race.getIdRace(),
                race.getNom(),
                race.getReputationWikiList().stream()
                        .map(reputationWiki -> new ReputationWikiVolatile(
                                reputationWiki.getIdReputationWiki(),
                                reputationWiki.getTerritoire(),
                                reputationWiki.getValeur()))
                        .toList(),
                race.getParticulariteList().stream()
                        .map(particularite -> new ParticulariteVolatile(
                                particularite.getIdParticularite(),
                                particularite.getNom(),
                                particularite.getDescription()))
                        .toList()
        );
    }

    //Convertir un DTO en entité persistante
    public Race toRaceEntity(RaceVolatile dto) {
        Race race = new Race();
        race.setNom(dto.getNom());

        // Mapper la liste de ReputationWikiVolatile en ReputationWiki (entité persistante)
        race.setReputationWikiList(
                dto.getReputationWikiList().stream()
                        .map(dtoReputationWiki -> {
                            ReputationWiki reputationWiki = new ReputationWiki();
                            reputationWiki.setIdReputationWiki(dtoReputationWiki.getIdReputationWiki());
                            reputationWiki.setTerritoire(dtoReputationWiki.getTerritoire());
                            reputationWiki.setValeur(dtoReputationWiki.getValeur());
                            return reputationWiki;
                        })
                        .toList() // Transforme la Stream en List
        );

        // Mapper la liste de ParticulariteVolatile en Particularite (entité persistante)
        race.setParticulariteList(
                dto.getParticulariteList().stream()
                        .map(dtoParticularite -> {
                            Particularite particularite = new Particularite();
                            particularite.setIdParticularite(dtoParticularite.getIdParticularite());
                            particularite.setNom(dtoParticularite.getNom());
                            particularite.setDescription(dtoParticularite.getDescription());
                            return particularite;
                        })
                        .toList() // Transforme la Stream en List
        );

        return race;
    }
}
