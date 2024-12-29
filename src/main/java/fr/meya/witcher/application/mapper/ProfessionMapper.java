package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.springframework.stereotype.Component;

@Component
public class ProfessionMapper {
    // Convertir l'entité persistante en DTO
    public ProfessionVolatile toProfessionDto(Profession profession) {
        return new ProfessionVolatile(
                profession.getNom(),
                profession.getCompetenceExclusive(),
                profession.getDescription(),
                profession.getCodeCaracteristique()
        );
    }

    // Convertir un DTO en entité persistante
    public Profession toProfessionEntity(ProfessionVolatile dto) {
        Profession profession = new Profession();
        profession.setNom(dto.getNom());
        profession.setCompetenceExclusive(dto.getCompetenceExclusive());
        profession.setDescription(dto.getDescription());
        profession.setCodeCaracteristique(dto.getCodeCaracteristique());
        return profession;
    }

}
