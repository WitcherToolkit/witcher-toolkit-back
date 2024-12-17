package fr.meya.witcher.domain.model.persistent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe représentant les compétences d'un personnage (connaissance de la rue, arbalète, etc...).
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Competence {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPETENCE")
	private long idCompetence;

	@NotBlank
	private String nom;

	@NotBlank
	@Column(name = "CODECARACTERISTIQUE")
	private String codeCaracteristique;

	@NotBlank
	private String description;

	@NotBlank
	@Column(name = "DESCRIPTIONBASE10")
	private String descriptionBase10;

	@NotBlank
	@Column(name = "DESCRIPTIONBASE13")
	private String descriptionBase13;

	@NotBlank
	@Column(name = "DESCRIPTIONBASE16")
	private String descriptionBase16;

	@NotBlank
	@Column(name = "DESCRIPTIONBASE20")
	private String descriptionBase20;

}
