package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.CompetenceProfessionVolatile;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessionMapper {

    private final CompetenceMapper competenceMapper;

    public ProfessionMapper(CompetenceMapper competenceMapper) {
        this.competenceMapper = competenceMapper;
    }

    // Convertir l'entité persistante en DTO
    public ProfessionVolatile toProfessionDto(Profession profession) {
        ProfessionVolatile dto = new ProfessionVolatile(
                profession.getIdProfession(),
                profession.getNom(),
                profession.getDescription(),
                new ArrayList<>() // Initialisation de la liste des compétences
        );

        if (profession.getCompetenceProfession() != null) {
            List<CompetenceProfessionVolatile> competenceProfessionVolatiles = profession.getCompetenceProfession().stream()
                    .map(cp -> new CompetenceProfessionVolatile(
                            cp.getIdCompetenceProfession(),
                            competenceMapper.toCompetenceDto(cp.getCompetence()), // Utilisation du CompetenceMapper
                            null // La profession n'est pas nécessaire ici pour éviter les boucles infinies
                    ))
                    .toList();
            dto.setCompetenceList(competenceProfessionVolatiles);
        }
        return dto;
    }

    // Convertir un DTO en entité persistante
    public Profession toProfessionEntity(ProfessionVolatile dto) {
        Profession profession = new Profession();
        profession.setNom(dto.getNom());
        profession.setDescription(dto.getDescription());
        return profession;
    }
}
