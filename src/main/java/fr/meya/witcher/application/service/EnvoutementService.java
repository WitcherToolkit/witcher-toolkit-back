package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.EnvoutementMapper;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.domain.port.in.IEnvoutementService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IEnvoutementRepository;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EnvoutementService implements IEnvoutementService {

    private final IEnvoutementRepository envoutementRepository;
    private final EnvoutementMapper envoutementMapper;
    private final ValidationUtils validationUtils;

    public EnvoutementService(IEnvoutementRepository envoutementRepository, EnvoutementMapper envoutementMapper, MessageSource messageSource) {
        this.envoutementRepository = envoutementRepository;
        this.envoutementMapper = envoutementMapper;
        this.validationUtils = new ValidationUtils(messageSource);
    }

    @Override
    public boolean isValid(EnvoutementVolatile envoutementVolatile) {
        if (envoutementVolatile == null) {
            throw new WitcherToolkitExeption("error.envoutement.null");
        }

        // Définir les règles avec clés de messages externalisées
        Map<String, ValidationRule> fieldRules = Map.of(
                "nom", new ValidationRule("error.envoutement.nom.required"),
                "cout", new ValidationRule("error.envoutement.cout.required"),
                "effet", new ValidationRule("error.envoutement.effet.required"),
                "prerequis", new ValidationRule("error.envoutement.prerequis.required"),
                "danger", new ValidationRule("error.envoutement.danger.required")
        );

        // Valider avec ValidationUtils
        validationUtils.validateWithRules(envoutementVolatile, fieldRules);

        return true;
    }

    @Override
    public List<EnvoutementVolatile> getEnvoutementList() {
        return envoutementRepository.findAll().stream().map(envoutementMapper::toEnvoutementDto).collect(Collectors.toList());
    }

    @Override
    public Envoutement getEnvoutement(Long idEnvoutement) {
        if (idEnvoutement == null) {
            throw new WitcherToolkitExeption("L'ID de l'envoûtement est null.");
        }

        return envoutementRepository.findById(idEnvoutement).orElseThrow(() -> new WitcherToolkitExeption("L'envoûtement avec l'ID " + idEnvoutement + " n'existe pas."));

    }

    @Override
    public Envoutement createEnvoutement(EnvoutementVolatile envoutementVolatile) {
        isValid(envoutementVolatile);

        Envoutement envoutement = envoutementMapper.toEnvoutementEntity(envoutementVolatile);
        return envoutementRepository.save(envoutement);
    }

    @Override
    public Envoutement updateEnvoutement(Long idEnvoutement, EnvoutementVolatile envoutementVolatile) {
        Envoutement envoutementExistant = envoutementRepository.findById(idEnvoutement)
                .orElseThrow(() -> new WitcherToolkitExeption("Envoûtement non trouvé"));

        return envoutementRepository.save(envoutementExistant);
    }

    @Override
    public void deleteEnvoutement(Long idEnvoutement) {
        Envoutement envoutementExistant = getEnvoutement(idEnvoutement);
        envoutementRepository.delete(envoutementExistant);
    }



}
