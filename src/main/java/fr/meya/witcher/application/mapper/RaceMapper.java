package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Particularite;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.model.persistent.ReputationWiki;
import fr.meya.witcher.message.response.ParticulariteVolatile;
import fr.meya.witcher.message.response.RaceVolatile;
import fr.meya.witcher.message.response.ReputationWikiVolatile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if (dto.getReputationWikiList() != null) {
            race.setReputationWikiList(
                dto.getReputationWikiList().stream()
                    .map(dtoReputationWiki -> {
                        ReputationWiki reputationWiki = new ReputationWiki();
                        reputationWiki.setTerritoire(dtoReputationWiki.getTerritoire());
                        reputationWiki.setValeur(dtoReputationWiki.getValeur());
                        reputationWiki.setRace(race); // Important : définir la relation
                        return reputationWiki;
                    })
                    .collect(Collectors.toList())
            );
        }


        if (dto.getParticulariteList() != null) {
            race.setParticulariteList(
                dto.getParticulariteList().stream()
                    .map(dtoParticularite -> {
                        Particularite particularite = new Particularite();
                        particularite.setNom(dtoParticularite.getNom());
                        particularite.setDescription(dtoParticularite.getDescription());
                        particularite.setRace(race); // Important : définir la relation
                        return particularite;
                    })
                    .collect(Collectors.toList())
            );
        }

        return race;
    }

    /**
     * Met à jour une entité Race existante avec les données d'un DTO
     * @param raceExistant L'entité Race à mettre à jour
     * @param raceVolatile Le DTO contenant les nouvelles données
     */
    public void updateRaceFromVolatile(Race raceExistant, RaceVolatile raceVolatile) {
        raceExistant.setNom(raceVolatile.getNom());

        // Gestion des ReputationWiki
        if (raceVolatile.getReputationWikiList() != null) {
            // Supprimer les réputations qui ne sont plus présentes
            raceExistant.getReputationWikiList().removeIf(existingReputation ->
                    raceVolatile.getReputationWikiList().stream()
                            .noneMatch(newReputation ->
                                    newReputation.getIdReputationWiki() != null &&
                                            newReputation.getIdReputationWiki().equals(existingReputation.getIdReputationWiki())
                            )
            );

            // Mettre à jour ou ajouter les nouvelles réputations
            raceVolatile.getReputationWikiList().forEach(reputationDto -> {
                if (reputationDto.getIdReputationWiki() != null) {
                    // Mettre à jour une réputation existante
                    raceExistant.getReputationWikiList().stream()
                            .filter(r -> r.getIdReputationWiki().equals(reputationDto.getIdReputationWiki()))
                            .findFirst()
                            .ifPresent(r -> {
                                r.setTerritoire(reputationDto.getTerritoire());
                                r.setValeur(reputationDto.getValeur());
                            });
                } else {
                    // Créer une nouvelle réputation
                    ReputationWiki newReputation = new ReputationWiki();
                    newReputation.setRace(raceExistant);
                    newReputation.setTerritoire(reputationDto.getTerritoire());
                    newReputation.setValeur(reputationDto.getValeur());
                    raceExistant.getReputationWikiList().add(newReputation);
                }
            });
        }

        // Même logique pour les Particularites
        if (raceVolatile.getParticulariteList() != null) {
            raceExistant.getParticulariteList().removeIf(existingParticularite ->
                    raceVolatile.getParticulariteList().stream()
                            .noneMatch(newParticularite ->
                                    newParticularite.getIdParticularite() != null &&
                                            newParticularite.getIdParticularite().equals(existingParticularite.getIdParticularite())
                            )
            );

            raceVolatile.getParticulariteList().forEach(particulariteDto -> {
                if (particulariteDto.getIdParticularite() != null) {
                    raceExistant.getParticulariteList().stream()
                            .filter(p -> p.getIdParticularite().equals(particulariteDto.getIdParticularite()))
                            .findFirst()
                            .ifPresent(p -> {
                                p.setNom(particulariteDto.getNom());
                                p.setDescription(particulariteDto.getDescription());
                            });
                } else {
                    Particularite newParticularite = new Particularite();
                    newParticularite.setRace(raceExistant);
                    newParticularite.setNom(particulariteDto.getNom());
                    newParticularite.setDescription(particulariteDto.getDescription());
                    raceExistant.getParticulariteList().add(newParticularite);
                }
            });
        }
    }

}
