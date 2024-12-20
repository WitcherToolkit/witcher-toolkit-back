package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les caractéristiques d'un personnage (DEX, END, etc...)
 */
@Getter
@Setter
@Entity
public class Caracteristique {

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;

	@NotBlank
	private String nom;

	@NotBlank
	private String code;

	@NotBlank
	private String description;

}
