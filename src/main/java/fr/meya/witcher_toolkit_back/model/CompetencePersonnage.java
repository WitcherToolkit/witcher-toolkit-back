package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque compétence du personnage.
 */
@Getter
@Setter
public class CompetencePersonnage {

	private int id;

	private Competence idCompetence;

	private Personnage idPersonnage;

	private  int valeurMax;

	private int valeurActuelle;
}
