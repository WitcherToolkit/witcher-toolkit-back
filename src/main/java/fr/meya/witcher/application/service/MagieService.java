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
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

        // Définir les règles avec clés de messages externalisées
        Map<String, ValidationRule> fieldRules = Map.of(
                "nom", new ValidationRule("error.magie.nom.required"),
                "cout", new ValidationRule("error.magie.cout.required"),
                "effet", new ValidationRule("error.magie.effet.required"),
                "duree", new ValidationRule("error.magie.duree.required"),
                "niveau", new ValidationRule("error.magie.niveau.required"),
                "profession", new ValidationRule("error.magie.profession.required")
        );

        // Valider avec ValidationUtils
        validationUtils.validateWithRules(magieVolatile, fieldRules);

        return true;
    }

    @Override
    public List<MagieVolatile> getMagieList() {
        return iMagieRepository.findAll().stream().map(magieMapper::toMagieDto).toList();
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

        Magie magieExistant = iMagieRepository.findById(idMagie)
                .orElseThrow(() -> new WitcherToolkitExeption("Magie non trouvée"));

        BeanUtils.copyProperties(magieVolatile, magieExistant, ObjectUtils.getNullPropertyNames(magieVolatile));

        return iMagieRepository.save(magieExistant);

    }

    @Override
    public void deleteMagie(Long idMagie) {
        Magie magieExistant = getMagie(idMagie);
        iMagieRepository.delete(magieExistant);
    }

}
