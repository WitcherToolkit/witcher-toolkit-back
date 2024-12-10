package fr.meya.witcher_toolkit_back.model.persistent;

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

	private String codeCaracteristique;

	private String description;

}
