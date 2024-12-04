package fr.meya.witcher_toolkit_back.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
public class Caracteristique {

	private int id;

	@NotBlank
	private String nom;

	@NotBlank
	private String code;

	@NotBlank
	private String description;

}
