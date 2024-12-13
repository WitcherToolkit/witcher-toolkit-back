package fr.meya.witcher.model.persistent;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant les valeurs réelles et maximales de chaque compétence du personnage.
 */
@Getter
@Setter
@Entity
public class CompetencePersonnage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDCOMPETENCEPERSONNAGE")
	private long idCompetencePersonnage;

	@ManyToOne
	@JoinColumn(name = "IDCOMPETENCE", nullable = false)
	private Competence competence;

	@ManyToOne
	@JoinColumn(name = "IDPERSONNAGE", nullable = false)
	private Personnage personnage;

	@NotBlank
	private int valeurActuelle;

	@NotBlank
	private int valeurMax;

}
