package fr.meya.witcher.domain.model.persistent;

import fr.meya.witcher.domain.model.key.CompetencePersonnageId;
import fr.meya.witcher.domain.model.key.CompetenceProfessionId;
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

	@EmbeddedId
	private CompetencePersonnageId id;

	@ManyToOne
	@MapsId("idCompetence")
	@JoinColumn(name = "id_competence", nullable = false)
	private Competence competence;

	@ManyToOne
	@MapsId("idPersonnage")
	@JoinColumn(name = "id_personnage", nullable = false)
	private Personnage personnage;

	@NotBlank
	private int valeurActuelle;

	@NotBlank
	private int valeurMax;

}
