package fr.meya.witcher.application.service;

import fr.meya.witcher.application.mapper.ProfessionMapper;
import fr.meya.witcher.common.utils.ObjectUtils;
import fr.meya.witcher.common.utils.ValidationRule;
import fr.meya.witcher.common.utils.ValidationUtils;
import fr.meya.witcher.domain.model.persistent.CompetenceProfession;
import fr.meya.witcher.domain.model.persistent.InventaireWiki;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.domain.port.in.IProfessionService;
import fr.meya.witcher.exeption.WitcherToolkitExeption;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepository;
import fr.meya.witcher.message.response.ProfessionVolatile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
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

        // Lier chaque enfant à la profession parente pour le cascade persist
        if (profession.getInventaireWikiList() != null) {
            for (InventaireWiki inv : profession.getInventaireWikiList()) {
                inv.setProfession(profession);
            }
        }
        if (profession.getCompetenceProfessionList() != null) {
            for (CompetenceProfession cp : profession.getCompetenceProfessionList()) {
                cp.setProfession(profession);
            }
        }

        return iProfessionRepository.save(profession);
    }

    @Override
    public Profession updateProfession(Long idProfession, ProfessionVolatile professionVolatile) {

        log.info("Début de la méthode updateProfession - ID : {}, nom : {}, compétences reçues : {}",
                idProfession,
                professionVolatile.getNom(),
                professionVolatile.getCompetenceList() != null
                        ? professionVolatile.getCompetenceList().stream()
                        .map(cp -> String.format("%s (id=%d)", cp.getCompetence().getNom(), cp.getCompetence().getIdCompetence()))
                        .collect(Collectors.joining(", "))
                        : "Aucune"
        );

        isValid(professionVolatile);
        log.info("Validation des données réussie");

        Profession professionExistant = iProfessionRepository.findById(idProfession)
                .orElseThrow(() -> new WitcherToolkitExeption("Profession non trouvée"));

        professionMapper.updateProfessionFromVolatile(professionVolatile, professionExistant);

        // Rattache chaque inventaire à la profession existante
        if (professionExistant.getInventaireWikiList() != null) {
            for (InventaireWiki inv : professionExistant.getInventaireWikiList()) {
                inv.setProfession(professionExistant);
            }
        }

        for (CompetenceProfession cp : professionExistant.getCompetenceProfessionList()) {
            cp.setProfession(professionExistant);
        }

        log.info("Objet Profession à sauvegarder : Profession[id={}, nom={}, compétences={}]",
                professionExistant.getIdProfession(),
                professionExistant.getNom(),
                professionExistant.getCompetenceProfessionList() != null
                        ? professionExistant.getCompetenceProfessionList().stream()
                        .map(cp -> String.format("%s (id=%d)", cp.getCompetence().getNom(), cp.getCompetence().getIdCompetence()))
                        .collect(Collectors.joining(", "))
                        : "Aucune"
        );

        return iProfessionRepository.save(professionExistant);
    }

        @Override
    public void deleteProfession(Long idProfession) {
        Profession professionExistant = getProfession(idProfession);
        iProfessionRepository.delete(professionExistant);
    }
}
