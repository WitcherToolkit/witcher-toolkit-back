package fr.meya.witcher.message.response;

import fr.meya.witcher.domain.model.persistent.Competence;
import fr.meya.witcher.domain.model.persistent.Personnage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompetencePersonnageVolatile {
    private Competence competence;

    // ne pas mettre car créer une dépendence cyclique :
    //private Personnage personnage;

    private int valeurActuelle;

    private int valeurMax;
}
