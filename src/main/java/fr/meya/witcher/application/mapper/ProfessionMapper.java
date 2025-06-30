package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.ProfessionVolatile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProfessionMapper {
    // Convertir l'entité persistante en DTO
    public ProfessionVolatile toProfessionDto(Profession profession) {
        return new ProfessionVolatile(
                profession.getNom(),
                profession.getDescription(),
                new ArrayList<>()
        );
    }

    // Convertir un DTO en entité persistante
    public Profession toProfessionEntity(ProfessionVolatile dto) {
        Profession profession = new Profession();
        profession.setNom(dto.getNom());
        profession.setDescription(dto.getDescription());
        return profession;
    }

}
