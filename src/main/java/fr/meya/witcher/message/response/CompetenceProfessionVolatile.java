package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceProfessionVolatile {
    private UUID idCompetenceProfession;

    private UUID idProfession;

    private UUID idCompetence;

    private CompetenceVolatile competence;

    // ne pas mettre car créer une dépendence cyclique :
    // private ProfessionVolatile profession;

}
