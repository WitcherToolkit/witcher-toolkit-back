package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.RituelMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Rituel;
import fr.meya.witcher.domain.port.in.IRituelService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IRituelRepository;
import fr.meya.witcher.message.response.RituelVolatile;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RituelService implements IRituelService {

    private final IRituelRepository iRituelRepository;
    private final RituelMapper rituelMapper;
    private final ValidationUtils validationUtils;

    public RituelService(IRituelRepository iRituelRepository, RituelMapper rituelMapper, MessageSource messageSource) {
        this.iRituelRepository = iRituelRepository;
        this.rituelMapper = rituelMapper;
        this.validationUtils = new ValidationUtils(messageSource); // Injecter le MessageSource
    }

    @Override
    public boolean isValid(RituelVolatile rituelVolatile) {
        if (rituelVolatile == null) {
            throw new WitcherToolkitExeption("error.rituel.null");
        }

        // Définir les règles avec clés de messages externalisées
        Map<String, ValidationRule> fieldRules = Map.of(
                "nom", new ValidationRule("error.rituel.nom.required"),
                "cout", new ValidationRule("error.rituel.cout.required"),
                "effet", new ValidationRule("error.rituel.effet.required"),
                "tempPreparation", new ValidationRule("error.rituel.tempPreparation.required"),
                "sd", new ValidationRule("error.rituel.sd.required"),
                "duree", new ValidationRule("error.rituel.duree.required"),
                "composant", new ValidationRule("error.rituel.composant.required"),
                "niveau", new ValidationRule("error.rituel.niveau.required")
        );

        // Valider avec ValidationUtils
        validationUtils.validateWithRules(rituelVolatile, fieldRules);

        return true;
    }

    @Override
    public List<RituelVolatile> getRituelList() {
        return iRituelRepository.findAll().stream().map(rituelMapper::toRituelDto).toList();
    }

    @Override
    public Rituel getRituel(Long idRituel) {

        if (idRituel == null) {
            throw new WitcherToolkitExeption("L'ID de la rituel est null.");
        }

        return iRituelRepository.findById(idRituel).orElseThrow(() -> new WitcherToolkitExeption("La rituel avec l'ID " + idRituel + " n'existe pas."));

    }

    @Override
    public Rituel createRituel(RituelVolatile rituelVolatile) {
        isValid(rituelVolatile);

        Rituel rituel = rituelMapper.toRituelEntity(rituelVolatile);
        return iRituelRepository.save(rituel);
    }

    @Override
    public Rituel updateRituel(Long idRituel, RituelVolatile rituelVolatile) {

        Rituel rituelExistant = iRituelRepository.findById(idRituel)
                .orElseThrow(() -> new WitcherToolkitExeption("Rituel non trouvée"));

        BeanUtils.copyProperties(rituelVolatile, rituelExistant, ObjectUtils.getNullPropertyNames(rituelVolatile));

        return iRituelRepository.save(rituelExistant);

    }

    @Override
    public void deleteRituel(Long idRituel) {
        Rituel rituelExistant = getRituel(idRituel);
        iRituelRepository.delete(rituelExistant);
    }
}
