package fr.meya.witcher_toolkit_back.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
public class Caracteristique {

	private int id;

	private String nom;

	private String description;

	private String competence;

}
