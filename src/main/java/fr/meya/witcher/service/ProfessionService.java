package fr.meya.witcher.service;

import fr.meya.witcher.model.message.CompetenceSpecifiqueVolatile;
import fr.meya.witcher.model.message.ProfessionVolatile;
import fr.meya.witcher.model.persistent.CompetenceSpecifique;
import fr.meya.witcher.model.persistent.Profession;
import fr.meya.witcher.adapter.port.out.IProfessionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionService implements IProfessionService{

    private final IProfessionRepository iProfessionRepository;

    public ProfessionService(IProfessionRepository iProfessionRepository) {
        this.iProfessionRepository = iProfessionRepository;
    }

    @Override
    public List<ProfessionVolatile> getListeDesProfessionsVolatiles() {
        // Récupérer les professions avec leurs compétences
        List<Profession> professions = iProfessionRepository.getListeDesProfessionsAvecCompetences();

        // Convertir les entités en DTO
        return professions.stream().map(profession -> {
            // Mapper la Profession vers ProfessionVolatile
            ProfessionVolatile pv = new ProfessionVolatile();
            pv.setIdProfession(profession.getIdProfession());
            pv.setNom(profession.getNom());
            pv.setCompetenceExclusive(profession.getCompetenceExclusive());
            pv.setDescription(profession.getDescription());
            pv.setCodeCaracteristique(profession.getCodeCaracteristique());

            // Mapper les CompetenceSpecifique vers CompetenceSpecifiqueVolatile
            List<CompetenceSpecifiqueVolatile> competences = profession.getCompetenceSpecifique().stream()
                    .sorted(Comparator.comparing(CompetenceSpecifique::getNom))
                    .map(cs -> {
                        CompetenceSpecifiqueVolatile csv = new CompetenceSpecifiqueVolatile();
                        csv.setIdCompetenceSpecifique(cs.getIdCompetenceSpecifique());
                        csv.setNom(cs.getNom());
                        csv.setDescription(cs.getDescription());
                        csv.setCodeCaracteristique(cs.getCodeCaracteristique());
                        csv.setSpecialisation(cs.getSpecialisation());
                        csv.setPrerequis(cs.getPrerequis());
                        return csv;
                    }).collect(Collectors.toList());

            pv.setCompetenceSpecifique(competences);

            return pv;
        }).collect(Collectors.toList());
    }

}
