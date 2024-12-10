package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque compétence du personnage.
 */
@Getter
@Setter
public class CompetenceGeneralePersonnage {

	private int id;

	private CompetenceGenerale competenceGenerale;

	private Personnage personnage;

	private  int valeur;
}
