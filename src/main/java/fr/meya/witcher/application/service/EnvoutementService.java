package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.EnvoutementMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Envoutement;
import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.domain.port.in.IEnvoutementService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IEnvoutementRepository;
import fr.meya.witcher.message.response.EnvoutementVolatile;
import fr.meya.witcher.message.response.MagieVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

        return true;
    }

    @Override
    public List<EnvoutementVolatile> getEnvoutementList() {
        return envoutementRepository.findAll().stream()
                .map(envoutementMapper::toEnvoutementDto).collect(Collectors.toList());
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
        log.info("Début de la méthode updateEnvoutement - ID : {} - Données reçues : {}", idEnvoutement, envoutementVolatile);

        isValid(envoutementVolatile);
        log.info("Validation des données réussie");

        Envoutement envoutementExistant = envoutementRepository.findById(idEnvoutement)
                .orElseThrow(() -> new WitcherToolkitExeption("Envoutement non trouvée"));
        log.info("Envoutement existante trouvée - Nom: {}, Danger: {}",
                envoutementExistant.getNom(),
                envoutementExistant.getDanger());

        log.info("Avant copyProperties - Nom: {}, Danger: {}",
                envoutementExistant.getNom(),
                envoutementExistant.getDanger());

        BeanUtils.copyProperties(envoutementVolatile, envoutementExistant, ObjectUtils.getNullPropertyNames(envoutementVolatile));

        log.info("Après copyProperties - Nom: {}, Danger: {}",
                envoutementExistant.getNom(),
                envoutementExistant.getDanger());

        Envoutement envoutementSauvegardee = envoutementRepository.save(envoutementExistant);
        log.info("Après sauvegarde - Nom: {}, Danger: {}",
                envoutementExistant.getNom(),
                envoutementExistant.getDanger());

        return envoutementSauvegardee;
    }

    @Override
    public void deleteEnvoutement(Long idEnvoutement) {
        Envoutement envoutementExistant = getEnvoutement(idEnvoutement);
        envoutementRepository.delete(envoutementExistant);
    }

}
