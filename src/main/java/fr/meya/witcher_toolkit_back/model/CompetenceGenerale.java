package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
public class CompetenceGenerale extends Competence{

	private String descriptionBase10;

	private String descriptionBase13;

	private String descriptionBase16;

	private String descriptionBase20;

}
