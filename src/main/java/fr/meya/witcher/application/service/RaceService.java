package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.RaceMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Race;
import fr.meya.witcher.domain.port.in.IRaceService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IRaceRepository;
import fr.meya.witcher.message.response.RaceVolatile;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        Map<String, ValidationRule> fieldRules = Map.of(
        "nom", new ValidationRule("error.race.nom.required")
        );

        validationUtils.validateWithRules(raceVolatile, fieldRules);

        return true;
    }

    @Override
    public List<RaceVolatile>getRaceList(){
        return raceRepository.findAll().stream().map(raceMapper::toRaceDto).toList();
    }

    @Override
    public Race getRace(Long idRace) {
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
    public Race updateRace(Long idRace, RaceVolatile raceVolatile){
        Race raceExistant = raceRepository.findById(idRace)
                .orElseThrow(() -> new WitcherToolkitExeption("Caractéristique non trouvée"));

        BeanUtils.copyProperties(raceVolatile, raceExistant, ObjectUtils.getNullPropertyNames(raceVolatile));

        return raceRepository.save(raceExistant);
    }

    @Override
    public void deleteRace(Long idRace) {
        Race raceExistant = getRace(idRace);
        raceRepository.delete(raceExistant);
    }

}
