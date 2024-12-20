package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.springframework.stereotype.Component;

@Component
public class CompetenceMapper {

    // Convertir l'entité persistante en DTO
    public CompetenceVolatile toCompetenceDto(Competence competence) {
        return new CompetenceVolatile(
                competence.getNom(),
                competence.getCodeCaracteristique(),
                competence.getDescription(),
                competence.getDescriptionBase10(),
                competence.getDescriptionBase13(),
                competence.getDescriptionBase16(),
                competence.getDescriptionBase20()
        );
    }

    // Convertir un DTO en entité persistante
    public Competence toCompetenceEntity(CompetenceVolatile dto) {
        Competence competence = new Competence();
        competence.setNom(dto.getNom());
        competence.setCodeCaracteristique(dto.getCodeCaracteristique());
        competence.setDescription(dto.getDescription());
        competence.setDescriptionBase10(dto.getDescriptionBase10());
        competence.setDescriptionBase13(dto.getDescriptionBase13());
        competence.setDescriptionBase16(dto.getDescriptionBase16());
        competence.setDescriptionBase20(dto.getDescriptionBase20());
        return competence;
    }
}
