package fr.meya.witcher_toolkit_back.model.persistent;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
@Entity
public class Competence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompetence;

	@NotBlank
	private String nom;

	@NotBlank
	private String codeCaracteristique;

	@NotBlank
	private String descriptionBase10;

	@NotBlank
	private String descriptionBase13;

	@NotBlank
	private String descriptionBase16;

	@NotBlank
	private String descriptionBase20;

}
