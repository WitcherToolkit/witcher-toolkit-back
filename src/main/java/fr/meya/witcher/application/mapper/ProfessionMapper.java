package fr.meya.witcher.application.mapper;

import fr.meya.witcher.domain.model.persistent.Profession;
import fr.meya.witcher.message.response.CompetenceProfessionVolatile;
import fr.meya.witcher.message.response.InventaireWikiVolatile;
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
                profession.getVigueur(),
                profession.getMaxSort(),
                profession.getMaxRituel(),
                profession.getMaxEnvoutement(),
                profession.getMaxInvocation(),
                new ArrayList<>(), // Initialisation de la liste des compétences
                new ArrayList<>()
        );

        // Gestion des inventaires wiki
        if (profession.getInventaireWikiList() != null) {
            List<InventaireWikiVolatile> inventaireWikiVolatiles = profession.getInventaireWikiList().stream()
                    .map(iw -> new InventaireWikiVolatile(
                            iw.getIdInventaireWiki(),
                            iw.getQuantite(),
                            iw.getNom(),
                            iw.getType(),
                            iw.getEffet(),
                            iw.isSpecial()
                    ))
                    .toList();
            dto.setInventaireWikiList(inventaireWikiVolatiles);
        }

        // Gestion des compétences
        if (profession.getCompetenceProfessionList() != null) {
            List<CompetenceProfessionVolatile> competenceProfessionVolatiles = profession.getCompetenceProfessionList().stream()
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
        profession.setVigueur(dto.getVigueur());
        return profession;
    }
}
