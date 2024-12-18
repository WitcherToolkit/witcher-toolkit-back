package fr.meya.witcher.application.service;

import fr.meya.witcher.domain.port.in.IProfessionService;
import fr.meya.witcher.message.response.CompetenceSpecifiqueVolatile;
import fr.meya.witcher.message.response.ProfessionVolatile;
import fr.meya.witcher.domain.model.persistent.CompetenceSpecifique;
import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.infrastructure.adapter.out.IProfessionRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionService implements IProfessionService {

    private final IProfessionRepository iProfessionRepository;

    public ProfessionService(IProfessionRepository iProfessionRepository) {
        this.iProfessionRepository = iProfessionRepository;
    }

    @Override
    public List<ProfessionVolatile> getListeDesProfessionsVolatiles() {
        // Récupérer les professions avec leurs compétences
        List<Profession> professionList = iProfessionRepository.findAll();
        //List<Profession> professionList = iProfessionRepository.getListeDesProfessionsAvecCompetences();

        // Convertir les entités en DTO
        return professionList.stream().map(profession -> {
            // Mapper la Profession vers ProfessionVolatile
            ProfessionVolatile professionVolatile = new ProfessionVolatile();
            professionVolatile.setIdProfession(profession.getIdProfession());
            professionVolatile.setNom(profession.getNom());
            professionVolatile.setCompetenceExclusive(profession.getCompetenceExclusive());
            professionVolatile.setDescription(profession.getDescription());
            professionVolatile.setCodeCaracteristique(profession.getCodeCaracteristique());

            // Mapper les CompetenceSpecifique vers CompetenceSpecifiqueVolatile
            List<CompetenceSpecifiqueVolatile> competences = profession.getCompetenceSpecifique().stream()
                    .sorted(Comparator.comparing(CompetenceSpecifique::getNom))
                    .map(cs -> {
                        CompetenceSpecifiqueVolatile competenceSpecifiqueVolatile = new CompetenceSpecifiqueVolatile();
                        competenceSpecifiqueVolatile.setIdCompetenceSpecifique(cs.getIdCompetenceSpecifique());
                        competenceSpecifiqueVolatile.setNom(cs.getNom());
                        competenceSpecifiqueVolatile.setDescription(cs.getDescription());
                        competenceSpecifiqueVolatile.setCodeCaracteristique(cs.getCodeCaracteristique());
                        competenceSpecifiqueVolatile.setSpecialisation(cs.getSpecialisation());
                        competenceSpecifiqueVolatile.setPrerequis(cs.getPrerequis());
                        return competenceSpecifiqueVolatile;
                    }).collect(Collectors.toList());

            professionVolatile.setCompetenceSpecifique(competences);

            return professionVolatile;
        }).collect(Collectors.toList());
    }
}
