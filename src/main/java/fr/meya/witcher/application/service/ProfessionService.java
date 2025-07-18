package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.ProfessionMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.domain.port.in.IProfessionService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepository;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProfessionService implements IProfessionService {

    private final IProfessionRepository iProfessionRepository;
    private final ProfessionMapper professionMapper;
    private final ValidationUtils validationUtils;

    public ProfessionService(IProfessionRepository iProfessionRepository, ProfessionMapper professionMapper, MessageSource messageSource) {
        this.iProfessionRepository = iProfessionRepository;
        this.professionMapper = professionMapper;
        this.validationUtils = new ValidationUtils(messageSource); // Injecter le MessageSource
    }

    @Override
    public boolean isValid(ProfessionVolatile professionVolatile) {
        if (professionVolatile == null) {
            throw new WitcherToolkitExeption("error.profession.null");
        }

        // Définir les règles avec clés de messages externalisées
        Map<String, ValidationRule> fieldRules = Map.of(
                "nom", new ValidationRule("error.profession.nom.required"),
                "competenceExclusive", new ValidationRule("error.profession.competenceExclusive.required"),
                "description", new ValidationRule("error.profession.description.required"),
                "codeCaracteristique", new ValidationRule("error.profession.codeCaracteristique.required")
        );

        // Valider avec ValidationUtils
        validationUtils.validateWithRules(professionVolatile, fieldRules);

        return true;
    }

    @Override
    public List<ProfessionVolatile> getProfessionList() {
        return iProfessionRepository.findAll().stream().map(professionMapper::toProfessionDto).toList();
    }

    @Override
    public Profession getProfession(Long idProfession) {

        if (idProfession == null) {
            throw new WitcherToolkitExeption("L'ID de la profession est null.");
        }

        return iProfessionRepository.findById(idProfession).orElseThrow(() -> new WitcherToolkitExeption("La profession avec l'ID " + idProfession + " n'existe pas."));

    }

    @Override
    public ProfessionVolatile getProfessionWithCompetences(Long idProfession) {
        if (idProfession == null) {
            throw new WitcherToolkitExeption("L'ID de la profession est null.");
        }
        Profession profession = iProfessionRepository.findById(idProfession)
                .orElseThrow(() -> new WitcherToolkitExeption("La profession avec l'ID " + idProfession + " n'existe pas."));

        // Mapper l'entité Profession (avec ses compétences chargées) vers le DTO ProfessionVolatile
        return professionMapper.toProfessionDto(profession);
    }

    @Override
    public Profession createProfession(ProfessionVolatile professionVolatile) {
        isValid(professionVolatile);

        Profession profession = professionMapper.toProfessionEntity(professionVolatile);
        return iProfessionRepository.save(profession);
    }

    @Override
    public Profession updateProfession(Long idProfession, ProfessionVolatile professionVolatile) {

        Profession professionExistant = iProfessionRepository.findById(idProfession)
                .orElseThrow(() -> new WitcherToolkitExeption("Profession non trouvée"));

        BeanUtils.copyProperties(professionVolatile, professionExistant, ObjectUtils.getNullPropertyNames(professionVolatile));

        return iProfessionRepository.save(professionExistant);

    }

    @Override
    public void deleteProfession(Long idProfession) {
        Profession professionExistant = getProfession(idProfession);
        iProfessionRepository.delete(professionExistant);
    }
}
