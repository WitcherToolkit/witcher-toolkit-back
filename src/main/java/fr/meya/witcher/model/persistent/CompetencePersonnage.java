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
	private int idCompetencePersonnage;

	@ManyToOne
	@JoinColumn(name = "idCompetence", nullable = false)
	private Competence competence;

	@ManyToOne
	@JoinColumn(name = "idPersonnage", nullable = false)
	private Personnage personnage;

	@NotBlank
	private int valeurActuelle;

	@NotBlank
	private int valeurMax;

}
