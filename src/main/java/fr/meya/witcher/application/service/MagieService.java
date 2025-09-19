package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.MagieMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Magie;
import fr.meya.witcher.domain.port.in.IMagieService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IMagieRepository;
import fr.meya.witcher.message.response.MagieVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MagieService implements IMagieService {

    private final IMagieRepository iMagieRepository;
    private final MagieMapper magieMapper;
    private final ValidationUtils validationUtils;

    public MagieService(IMagieRepository iMagieRepository, MagieMapper magieMapper, MessageSource messageSource) {
        this.iMagieRepository = iMagieRepository;
        this.magieMapper = magieMapper;
        this.validationUtils = new ValidationUtils(messageSource); // Injecter le MessageSource
    }

    @Override
    public boolean isValid(MagieVolatile magieVolatile) {
        if (magieVolatile == null) {
            throw new WitcherToolkitExeption("error.magie.null");
        }

        return true;
    }

    @Override
    public List<MagieVolatile> getMagieList(String niveau) {
        List<Magie> magies = (niveau == null)
                ? iMagieRepository.findAll()
                : iMagieRepository.findByNiveauIgnoreCase(niveau);

        return magies.stream()
                .map(magieMapper::toMagieDto)
                .sorted(Comparator.comparing(MagieVolatile::getNom, String.CASE_INSENSITIVE_ORDER))
                .toList();
    }

    @Override
    public Magie getMagie(Long idMagie) {

        if (idMagie == null) {
            throw new WitcherToolkitExeption("L'ID de la magie est null.");
        }

        return iMagieRepository.findById(idMagie).orElseThrow(() -> new WitcherToolkitExeption("La magie avec l'ID " + idMagie + " n'existe pas."));

    }

    @Override
    public Magie createMagie(MagieVolatile magieVolatile) {
        isValid(magieVolatile);

        Magie magie = magieMapper.toMagieEntity(magieVolatile);
        return iMagieRepository.save(magie);
    }

    @Override
    public Magie updateMagie(Long idMagie, MagieVolatile magieVolatile) {
        log.info("Début de la méthode updateMagie - ID : {} - Données reçues : {}", idMagie, magieVolatile);

        isValid(magieVolatile);
        log.info("Validation des données réussie");

        Magie magieExistant = iMagieRepository.findById(idMagie)
                .orElseThrow(() -> new WitcherToolkitExeption("Magie non trouvée"));
        log.info("Magie existante trouvée - Nom: {}, Nature: {}, Type: {}, Effet: {}",
                magieExistant.getNom(),
                magieExistant.getNature(),
                magieExistant.getType(),
                magieExistant.getEffet());

        log.info("Avant copyProperties - Nom: {}, Nature: {}, Type: {}",
                magieExistant.getNom(),
                magieExistant.getNature(),
                magieExistant.getType());

        BeanUtils.copyProperties(magieVolatile, magieExistant, ObjectUtils.getNullPropertyNames(magieVolatile));

        log.info("Après copyProperties - Nom: {}, Nature: {}, Type: {}",
                magieExistant.getNom(),
                magieExistant.getNature(),
                magieExistant.getType());

        Magie magieSauvegardee = iMagieRepository.save(magieExistant);
        log.info("Après sauvegarde - Nom: {}, Nature: {}, Type: {}",
                magieSauvegardee.getNom(),
                magieSauvegardee.getNature(),
                magieSauvegardee.getType());

        return magieSauvegardee;
    }

        @Override
    public void deleteMagie(Long idMagie) {
        Magie magieExistant = getMagie(idMagie);
        iMagieRepository.delete(magieExistant);
    }

}
