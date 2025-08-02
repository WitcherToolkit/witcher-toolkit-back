package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.message.response.CompetenceProfessionVolatile;
import fr.meya.witcher.message.response.CompetenceVolatile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CompetenceMapper {

    private final CaracteristiqueMapper caracteristiqueMapper;

    public CompetenceMapper(CaracteristiqueMapper caracteristiqueMapper) {
        this.caracteristiqueMapper = caracteristiqueMapper;
    }

    // Convertir l'entité persistante en DTO
    public CompetenceVolatile toCompetenceDto(Competence competence) {
        return new CompetenceVolatile(
                competence.getIdCompetence(),
                competence.getNom(),
                competence.getDescription(),
                competence.isExclusive(),
                competence.getPrerequis(),
                competence.getSpecialisation(),
                caracteristiqueMapper.toCaracteristiqueDto(competence.getCaracteristique())
        );
    }

    // Convertir un DTO en entité persistante
    public Competence toCompetenceEntity(CompetenceVolatile dto) {
        Competence competence = new Competence();
        competence.setNom(dto.getNom());
        competence.setDescription(dto.getDescription());
        competence.setPrerequis(dto.getPrerequis());
        competence.setSpecialisation(dto.getSpecialisation());
        competence.setExclusive(dto.isExclusive());
        return competence;
    }
}
