package fr.meya.witcher_toolkit_back.model.persistent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompetenceSpecifiquePersonnage {

	private int id;

	private CompetenceSpecifique competenceSpecifique;

	private Personnage personnage;

	private int valeurMax;

	private int valeurActuelle;
}
