package fr.meya.witcher.message.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristiquePersonnageVolatile {
    private CaracteristiqueVolatile caracteristique;

    // ne pas mettre car créer une dépendence cyclique :
    //private Personnage personnage;

    private int valeurActuelle;

    private int valeurMax;
}
