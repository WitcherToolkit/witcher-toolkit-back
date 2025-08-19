package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetenceProfessionVolatile {
    private long idCompetenceProfession;

    private Long idProfession;

    private Long idCompetence;

    private CompetenceVolatile competence;

    // ne pas mettre car créer une dépendence cyclique :
    // private ProfessionVolatile profession;

}
