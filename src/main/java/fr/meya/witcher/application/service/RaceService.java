package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.RaceMapper;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.port.in.IRaceService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IRaceRepository;
import fr.meya.witcher.message.response.RaceVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class RaceService implements IRaceService {

    private final IRaceRepository raceRepository;
    private final RaceMapper raceMapper;
    private final ValidationUtils validationUtils;

    public RaceService(IRaceRepository raceRepository, RaceMapper raceMapper, MessageSource messageSource) {
        this.raceRepository = raceRepository;
        this.raceMapper = raceMapper;
        this.validationUtils = new ValidationUtils(messageSource);
    }

    @Override
    public boolean isValid(RaceVolatile raceVolatile) {
        if (raceVolatile == null) {
            throw new IllegalArgumentException("error.race.null");
        }

        return true;
    }

    @Override
    public List<RaceVolatile>getRaceList(){
        return raceRepository.findAll().stream().map(raceMapper::toRaceDto).toList();
    }

    @Override
    public Race getRaceById(UUID idRace) {
        if (idRace == null) {
            throw new IllegalArgumentException("L'ID de la race est null.");
        }

        return raceRepository.findById(idRace).orElseThrow(() -> new IllegalArgumentException("La race avec l'ID " + idRace + " n'existe pas."));
    }

    @Override
    public Race createRace(RaceVolatile raceVolatile) {
        isValid(raceVolatile);

        Race race = raceMapper.toRaceEntity(raceVolatile);

        return raceRepository.save(race);
    }

    @Override
    public Race updateRace(UUID idRace, RaceVolatile raceVolatile){
        log.info("Début de la méthode updateRace - ID : {} - Données reçues : {}", idRace, raceVolatile);

        isValid(raceVolatile);
        log.info("Validation des données réussie");

        Race raceExistant = raceRepository.findById(idRace)
                .orElseThrow(() -> new WitcherToolkitExeption("Race non trouvée"));

        raceMapper.updateRaceFromVolatile(raceVolatile, raceExistant);

        log.info("Objet Race à sauvegarder : Race[id={}, nom={}, nbReputations={}, nbParticularites={}]",
                raceExistant.getIdRace(),
                raceExistant.getNom(),
                raceExistant.getReputationWikiList().size(),
                raceExistant.getParticulariteList().size());

        return raceRepository.save(raceExistant);
    }

    @Override
    public void deleteRace(UUID idRace) {
        Race raceExistant = getRaceById(idRace);
        raceRepository.delete(raceExistant);
    }

}
