package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
public class Competence {

	private int id;

	private String nom;

	private String description;

	// Compétence exclusive à au job
	private String type;

	// Dans le cas de montée de niveau
	private String prerequis;
}
